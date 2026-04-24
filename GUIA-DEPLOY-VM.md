# Guia Completo de Deploy da API na VM

## Objetivo

Subir dois containers na VM Linux:

1. API Java (Spring Boot)
2. Banco MySQL

Cenário real usado:

1. Máquina principal com Podman
2. VM com Docker

## Arquivos criados e usados

1. [Dockerfile](Dockerfile)
2. [docker-compose.yml](docker-compose.yml)
3. [docker-compose.prod.yml](docker-compose.prod.yml)
4. [src/main/resources/application.properties](src/main/resources/application.properties)
5. [README.md](README.md)

## Principais decisões técnicas

1. Build da imagem da API na máquina principal.
2. Publicação da imagem em registry (Docker Hub).
3. Na VM, usar apenas pull da imagem e subir com compose.
4. MySQL e API sobem na mesma rede do compose.
5. API acessa o banco usando hostname mysql (nome do serviço no compose).

## Problemas que aconteceram e como resolvemos

1. Erro no Podman com nome curto de imagem base.
   Descrição: Podman não resolveu maven:... e eclipse-temurin:... por falta de unqualified-search registries.
   Correção: usar imagens totalmente qualificadas no Dockerfile.

2. Erro de comando com Podman.
   Descrição: foi digitado podman podman build.
   Correção: comando correto é podman build -t ... .

3. Erro ao subir compose na VM: arquivo não encontrado.
   Descrição: docker compose -f docker-compose.prod.yml sem o arquivo existir na VM.
   Correção: copiar [docker-compose.prod.yml](docker-compose.prod.yml) para /home/opc/ ou rodar na pasta correta.

4. Erro de conflito de container.
   Descrição: nome velocitycar-api já estava em uso.
   Correção: parar e remover container antigo antes de subir novamente.

5. Erro 404 ao testar /carros.
   Descrição: endpoint publicado na imagem era /car (singular), não /carros.
   Correção: testar nos endpoints reais do controller.

## Fluxo final correto do zero

### Etapa 1. Build e push na máquina principal (Podman)

    cd /home/roque/Documentos/Velocity-Car-API
    podman build -t roque0arantes/velocitycar-api:1.0 .
    podman login docker.io
    podman push roque0arantes/velocitycar-api:1.0

### Etapa 2. Copiar compose para VM

    chmod 400 ~/Downloads/ssh-key-2026-04-24.key
    scp -i ~/Downloads/ssh-key-2026-04-24.key /home/roque/Documentos/Velocity-Car-API/docker-compose.prod.yml opc@163.176.177.107:/home/opc/

### Etapa 3. Subir na VM (Docker)

    ssh -i ~/Downloads/ssh-key-2026-04-24.key opc@163.176.177.107
    docker login
    docker pull roque0arantes/velocitycar-api:1.0
    API_IMAGE=roque0arantes/velocitycar-api:1.0 docker compose -f /home/opc/docker-compose.prod.yml up -d

### Etapa 4. Resolver conflito de nome (se ocorrer)

    docker stop velocitycar-api 2>/dev/null
    docker rm velocitycar-api
    API_IMAGE=roque0arantes/velocitycar-api:1.0 docker compose -f /home/opc/docker-compose.prod.yml up -d

## Como validar que está funcionando

### Ver containers

    docker ps

Resultado esperado:

1. velocitycar-api com status Up e porta 8080 publicada.
2. velocitycar-mysql com status Up (healthy) e porta 3306 publicada.

### Testar API dentro da VM

A versão atual da imagem usa endpoint singular.

    curl -i http://localhost:8080/car
    curl -i http://localhost:8080/client

### Testar API de fora da VM

    curl -i http://163.176.177.107:8080/car

Se funcionar dentro e falhar fora:

1. Abrir porta 8080 nas regras de rede da nuvem.
2. Abrir porta 8080 no firewall da VM.

## Comandos úteis de troubleshooting

### Ver logs da API

    docker logs -f velocitycar-api

### Ver logs do MySQL

    docker logs -f velocitycar-mysql

### Ver imagens disponíveis

    docker images

### Ver se compose existe na VM

    find /home/opc -name "docker-compose.prod.yml" 2>/dev/null

### Derrubar stack do compose

    docker compose -f /home/opc/docker-compose.prod.yml down --remove-orphans

## Checklist rápido para repetir o deploy

1. Buildar imagem com Podman.
2. Fazer push para Docker Hub.
3. Copiar docker-compose.prod.yml para VM.
4. Fazer pull da imagem na VM.
5. Subir com docker compose usando API_IMAGE.
6. Validar com docker ps.
7. Testar endpoint correto /car.

## Observação importante sobre endpoints

A documentação inicial citava rotas em plural, mas a imagem publicada respondeu em singular.
Para garantir consistência futura, revisar mappings dos controllers e alinhar com a documentação antes do próximo release.
