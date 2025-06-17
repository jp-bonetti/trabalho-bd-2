package org.satc.sistemamrp.embeddable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.satc.sistemamrp.model.MateriaPrima;
import org.satc.sistemamrp.model.Produto;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListaMateriaisId {

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produtoId;

    @ManyToOne
    @JoinColumn(name = "materia_prima_id", nullable = false)
    private MateriaPrima materiaPrimaId;
}
