package com.fatec.projeto1;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import jakarta.validation.ConstraintViolationException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import com.fatec.projeto1.model.Produto;
import com.fatec.projeto1.repository.ProdutoRepository;
import java.math.BigDecimal;

@DataJpaTest 

public class ProdutoRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
   
    @Autowired
    private ProdutoRepository produtoRepository;

    @Test
    @Transactional
    public void testSaveProdutoSuccessfully() {
        // Crie uma nova instância de Produto com valores válidos
        Produto produto = new Produto();
        produto.setCodigo("PROD123");
        produto.setDescricao("Produto Teste");
        produto.setPreco(new BigDecimal("19.99"));
        produto.setQuantidadeEstoque(10);

        // Salve o produto no banco de dados
        Produto savedProduto = produtoRepository.save(produto);

        // Verifique se o id foi gerado
        assertNotNull(savedProduto.getId());

        // Verifique se os campos salvos coincidem
        assertThat(savedProduto.getId()).isNotNull();
        assertThat(savedProduto.getCodigo()).isEqualTo("PROD123");
        assertThat(savedProduto.getDescricao()).isEqualTo("Produto Teste");
        assertThat(savedProduto.getPreco()).isEqualTo(new BigDecimal("19.99"));
        assertThat(savedProduto.getQuantidadeEstoque()).isEqualTo(10);

       
    }

    @Test
    @Transactional
    public void testSaveProdutoWithNegativePrice() {
        // Crie uma nova instância de Produto com valor negativo para preço
        Produto produto = new Produto();
        produto.setCodigo("PROD124");
        produto.setDescricao("Produto Teste");
        produto.setPreco(new BigDecimal("-19.99"));
        produto.setQuantidadeEstoque(10);

        // Verifique se a validação de restrição é lançada
        assertThrows(ConstraintViolationException.class, () -> {
            produtoRepository.save(produto);
        });

        //entityManager.getTransaction().commit();
    }

    @Test
    @Transactional
    public void testSaveProdutoWithNullCode() {
        // Crie uma nova instância de Produto com código nulo
        Produto produto = new Produto();
        produto.setCodigo(null);
        produto.setDescricao("Produto Teste");
        produto.setPreco(new BigDecimal("19.99"));
        produto.setQuantidadeEstoque(10);

        // Verifique se a validação de restrição é lançada
        assertThrows(ConstraintViolationException.class, () -> {
            produtoRepository.save(produto);
        });

       //entityManager.getTransaction().commit();
    }

    @Test
    @Transactional
    public void testSaveProdutoWithDuplicateCode() {
        // Crie uma nova instância de Produto com código duplicado
        produtoRepository.save(new Produto("PROD123", "Produto Teste", new BigDecimal("19.99"), 10));

        // Crie uma segunda instância de Produto com o mesmo código
        Produto produto = new Produto("PROD123", "Produto Teste", new BigDecimal("19.99"), 10);

        // Verifique se a validação de restrição é lançada
        assertThrows(org.springframework.dao.DataIntegrityViolationException.class, () -> {
            produtoRepository.save(produto);
        });

        //entityManager.getTransaction().commit();
    }
}
