package org.satc.sistemamrp.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Data;
import org.satc.sistemamrp.embeddable.ListaMateriaisId;

@Entity
@Data
public class ListaMateriais {

    @EmbeddedId
    private ListaMateriaisId listaMateriaisId;
    private Double quantidade;

}
