package br.com.fiap.velocitycar.validation;
import br.com.fiap.velocitycar.dto.ServiceOrderRequest;
import br.com.fiap.velocitycar.models.CarPart;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ServiceOrderValidator implements ConstraintValidator<ServiceOrderValidation, ServiceOrderRequest> {
    @Override
    public boolean isValid(ServiceOrderRequest serviceOrderRequest, ConstraintValidatorContext context) {
        if (serviceOrderRequest == null) {
            return true;
        }

        var valid = true;

        if (serviceOrderRequest.carParts() == null || serviceOrderRequest.carParts().isEmpty()) {
            valid = false;
            addViolation(context, "carParts", "A lista de peças não pode estar vazia");
            return false;
        }

        for (int i = 0; i < serviceOrderRequest.carParts().size(); i++) {
            CarPart carPart = serviceOrderRequest.carParts().get(i);

            if (carPart == null) {
                valid = false;
                addViolation(context, "carParts[" + i + "]", "Peça inválida");
                continue;
            }

            if (!fieldIsValid(carPart.getName())) {
                valid = false;
                addViolation(context, "carParts[" + i + "].name", "Nome da peça é obrigatório");
            }

            if (carPart.getQuantity() == null || carPart.getQuantity() <= 0) {
                valid = false;
                addViolation(context, "carParts[" + i + "].quantity", "Quantidade deve ser maior que zero");
            }

            if (carPart.getPrice() == null || carPart.getPrice() <= 0) {
                valid = false;
                addViolation(context, "carParts[" + i + "].price", "Preço deve ser maior que zero");
            }
        }

        return valid;
    }

    private boolean fieldIsValid(String value) {
        return value != null && !value.isBlank();
    }

    private void addViolation(ConstraintValidatorContext context, String field, String message) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message)
                .addPropertyNode(field)
                .addConstraintViolation();
    }
}
