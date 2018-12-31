package br.com.lfuso.cursos.spring.restful.order;

import br.com.lfuso.cursos.spring.restful.order.domain.Categoria;
import br.com.lfuso.cursos.spring.restful.order.domain.Cidade;
import br.com.lfuso.cursos.spring.restful.order.domain.Estado;
import br.com.lfuso.cursos.spring.restful.order.domain.Produto;
import br.com.lfuso.cursos.spring.restful.order.repositories.CategoriaRepository;
import br.com.lfuso.cursos.spring.restful.order.repositories.CidadeRepository;
import br.com.lfuso.cursos.spring.restful.order.repositories.EstadoRepository;
import br.com.lfuso.cursos.spring.restful.order.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class OrderApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	public static void main(String[] args) {
		SpringApplication.run(OrderApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria informatica = new Categoria(null, "Informática");
		Categoria escritorio = new Categoria(null, "Escritório");

		Produto computador = new Produto(null, "Computador", 2000.0);
		Produto impressora = new Produto(null, "Impressora", 800.0);
		Produto mouse = new Produto(null, "Mouse", 80.0);

		informatica.getProdutos().addAll(Arrays.asList(computador, impressora, mouse));
		escritorio.getProdutos().add(impressora);

		computador.getCategorias().add(informatica);
		impressora.getCategorias().addAll(Arrays.asList(informatica, escritorio));
		mouse.getCategorias().add(informatica);

		categoriaRepository.saveAll(Arrays.asList(informatica, escritorio));
		produtoRepository.saveAll(Arrays.asList(computador, impressora, mouse));

		Estado minasGerais = new Estado(null, "Minas Gerais");
		Estado saoPaulo = new Estado(null, "São Paulo");

		Cidade uberlandia = new Cidade(null, "Uberlândia", minasGerais);
		Cidade sp = new Cidade(null, "São Paulo", saoPaulo);
		Cidade campinas = new Cidade(null, "Campinas", saoPaulo);

		minasGerais.getCidades().add(uberlandia);
		saoPaulo.getCidades().addAll(Arrays.asList(sp, campinas));

		estadoRepository.saveAll(Arrays.asList(minasGerais, saoPaulo));
		cidadeRepository.saveAll(Arrays.asList(uberlandia, sp, campinas));
	}
}

