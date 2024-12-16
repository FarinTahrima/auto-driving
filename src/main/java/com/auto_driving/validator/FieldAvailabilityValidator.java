package com.auto_driving.validator;

import com.auto_driving.exception.OutOfSpaceException;
import com.auto_driving.model.RectangularField;

public class FieldAvailabilityValidator {

    public void validate() throws OutOfSpaceException {
        if(!RectangularField.getFieldManager().checkAvailability()) {
            throw new OutOfSpaceException(RectangularField.getFieldManager().getMaxCapacity());
        }
    }

}
