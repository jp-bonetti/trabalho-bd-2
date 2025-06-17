package org.satc.sistemamrp.controller;

import org.satc.sistemamrp.embeddable.ListaMateriaisId;
import org.satc.sistemamrp.model.ListaMateriais;
import org.satc.sistemamrp.model.MateriaPrima;
import org.satc.sistemamrp.model.Produto;
import org.satc.sistemamrp.service.ListaMateriaisService;
import org.satc.sistemamrp.service.MateriaPrimaService;
import org.satc.sistemamrp.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/listas-materiais")
public class ListaMateriaisController {

    @Autowired
    private ListaMateriaisService listaMateriaisService;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private MateriaPrimaService materiaPrimaService;

    @GetMapping
    public List<ListaMateriais> getAll() {
        return listaMateriaisService.findAll();
    }

    @GetMapping("/{produtoId}/{materiaPrimaId}")
    public ResponseEntity<ListaMateriais> getById(
            @PathVariable Integer produtoId,
            @PathVariable Integer materiaPrimaId) {
        Produto produto = produtoService.findById(produtoId)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));
        MateriaPrima materiaPrima = materiaPrimaService.findById(materiaPrimaId)
                .orElseThrow(() -> new IllegalArgumentException("Matéria-prima não encontrada"));

        ListaMateriaisId id = new ListaMateriaisId(produto, materiaPrima);

        return listaMateriaisService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ListaMateriais create(@RequestBody ListaMateriais listaMateriais) {
        return listaMateriaisService.save(listaMateriais);
    }

    @PutMapping("/{produtoId}/{materiaPrimaId}")
    public ResponseEntity<ListaMateriais> update(
            @PathVariable Integer produtoId,
            @PathVariable Integer materiaPrimaId,
            @RequestBody ListaMateriais listaMateriais) {
        Produto produto = produtoService.findById(produtoId)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));
        MateriaPrima materiaPrima = materiaPrimaService.findById(materiaPrimaId)
                .orElseThrow(() -> new IllegalArgumentException("Matéria-prima não encontrada"));

        ListaMateriaisId id = new ListaMateriaisId(produto, materiaPrima);

        return listaMateriaisService.findById(id)
                .map(existing -> {
                    listaMateriais.setListaMateriaisId(id);
                    return ResponseEntity.ok(listaMateriaisService.save(listaMateriais));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{produtoId}/{materiaPrimaId}")
    public ResponseEntity<Void> delete(
            @PathVariable Integer produtoId,
            @PathVariable Integer materiaPrimaId) {
        Produto produto = produtoService.findById(produtoId)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));
        MateriaPrima materiaPrima = materiaPrimaService.findById(materiaPrimaId)
                .orElseThrow(() -> new IllegalArgumentException("Matéria-prima não encontrada"));

        ListaMateriaisId id = new ListaMateriaisId(produto, materiaPrima);

        if (listaMateriaisService.findById(id).isPresent()) {
            listaMateriaisService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}