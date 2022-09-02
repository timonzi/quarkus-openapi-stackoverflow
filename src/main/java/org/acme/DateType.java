package org.acme;

import java.util.Date;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(implementation = Date.class, name = "DateType")
public class DateType extends Type<Date> {

    public Date getDefaultValue() {
        return null;
    }

    @Override
    public int compareTo(final Type<Date> o) {
        return 0;
    }
}
