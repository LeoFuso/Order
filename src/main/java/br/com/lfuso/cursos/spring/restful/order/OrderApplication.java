package br.com.lfuso.cursos.spring.restful.order;

import br.com.lfuso.cursos.spring.restful.order.domain.*;
import br.com.lfuso.cursos.spring.restful.order.domain.enums.EstadoPagamento;
import br.com.lfuso.cursos.spring.restful.order.domain.enums.TipoCliente;
import br.com.lfuso.cursos.spring.restful.order.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
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

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

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
		impressora.getCategorias().addAll(Arrays.asList(escritorio, informatica));
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

		Cliente maria = new Cliente
				(null, "Maria Silva", "maria@gmail.com", "39378912377", TipoCliente.PESSOA_FISICA);

		maria.getTelefones().addAll(Arrays.asList("27363223", "98838393"));

		Endereco flores = new Endereco
				(null, "Rua Flores", "300", "Apto 203", "Jardim", "38220634", maria, uberlandia);

		Endereco matos = new Endereco
				(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", maria, sp);

		maria.getEnderecos().addAll(Arrays.asList(flores, matos));

		clienteRepository.save(maria);
		enderecoRepository.saveAll(Arrays.asList(flores, matos));

		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyy HH:mm");

		Pedido pedido1 = new Pedido(null, format.parse("30/09/2017 10:32"), maria, flores);
		Pedido pedido2 = new Pedido(null, format.parse("10/10/2017 19:25"), maria, matos);

		Pagamento pagamento1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, pedido1, 6);
		pedido1.setPagamento(pagamento1);

		Pagamento pagamento2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, pedido2, format.parse("20/10/2017 23:59"), null);
		pedido2.setPagamento(pagamento2);

		maria.getPedidos().addAll(Arrays.asList(pedido1, pedido2));

		pedidoRepository.saveAll(Arrays.asList(pedido1, pedido2));
		pagamentoRepository.saveAll(Arrays.asList(pagamento1, pagamento2));

		ItemPedido itemPedido1 = new ItemPedido(pedido1, computador, 0.0, 1, 2000.0);
		ItemPedido itemPedido2 = new ItemPedido(pedido1, mouse, 0.0, 2, 80.0);
		ItemPedido itemPedido3 = new ItemPedido(pedido2, impressora, 100.0, 1, 800.0);

		pedido1.getItens().addAll(Arrays.asList(itemPedido1, itemPedido2));
		pedido2.getItens().add(itemPedido3);

		computador.getItens().add(itemPedido1);
		impressora.getItens().add(itemPedido3);
		mouse.getItens().add(itemPedido2);

		itemPedidoRepository.saveAll(Arrays.asList(itemPedido1, itemPedido2, itemPedido3));
	}
}

