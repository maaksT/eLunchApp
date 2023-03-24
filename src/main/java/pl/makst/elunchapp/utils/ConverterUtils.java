package pl.makst.elunchapp.utils;

import pl.makst.elunchapp.DTO.DelivererDTO;
import pl.makst.elunchapp.model.Deliverer;

public class ConverterUtils {
    public static DelivererDTO convert(Deliverer deliverer) {
        DelivererDTO delivererDTO = new DelivererDTO();
        delivererDTO.setUuid(deliverer.getUuid());
        ///...
        return delivererDTO;
    }
}
