package com.fatec.projeto1.model;

import java.math.BigDecimal;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "tb_produto")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Column(nullable = false, unique = true)
	private String codigo;

	@NotBlank
	@Column(nullable = false)
	private String descricao;

	@NotNull
	@PositiveOrZero
	@Column(nullable = false, precision = 12, scale = 2)
	private BigDecimal preco;

	@NotNull
	@PositiveOrZero
	@Column(nullable = false)
	private Integer quantidadeEstoque;

	public Produto() {
	}

	public Produto(String codigo, String descricao, BigDecimal preco, Integer quantidadeEstoque) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.preco = preco;
		this.quantidadeEstoque = quantidadeEstoque;
	}

	public Produto(Long id, String codigo, String descricao, BigDecimal preco, Integer quantidadeEstoque) {
		this(codigo, descricao, preco, quantidadeEstoque);
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Integer getQuantidadeEstoque() {
		return quantidadeEstoque;
	}

	public void setQuantidadeEstoque(Integer quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}

	@Override
	public boolean equals(Object objeto) {
		if (this == objeto) {
			return true;
		}
		if (!(objeto instanceof Produto outro)) {
			return false;
		}
		return id != null && Objects.equals(id, outro.id);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}
}
