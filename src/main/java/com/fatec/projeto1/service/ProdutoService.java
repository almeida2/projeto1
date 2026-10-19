package com.fatec.projeto1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fatec.projeto1.model.Produto;
import com.fatec.projeto1.repository.ProdutoRepository;

@Service
public class ProdutoService {

	private final ProdutoRepository produtoRepository;

	public ProdutoService(ProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}

	@Transactional
	public Produto salvar(Produto produto) {
		return produtoRepository.save(produto);
	}

	@Transactional(readOnly = true)
	public List<Produto> listarTodos() {
		return produtoRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Optional<Produto> buscarPorId(Long id) {
		return produtoRepository.findById(id);
	}

	@Transactional
	public Optional<Produto> atualizar(Long id, Produto produtoAtualizado) {
		return produtoRepository.findById(id).map(produto -> {
			produto.setCodigo(produtoAtualizado.getCodigo());
			produto.setDescricao(produtoAtualizado.getDescricao());
			produto.setPreco(produtoAtualizado.getPreco());
			produto.setQuantidadeEstoque(produtoAtualizado.getQuantidadeEstoque());
			return produtoRepository.save(produto);
		});
	}

	@Transactional
	public boolean deletar(Long id) {
		if (!produtoRepository.existsById(id)) {
			return false;
		}
		produtoRepository.deleteById(id);
		return true;
	}
}
