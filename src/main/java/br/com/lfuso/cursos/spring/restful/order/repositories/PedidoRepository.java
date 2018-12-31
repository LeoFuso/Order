package br.com.lfuso.cursos.spring.restful.order.repositories;

import br.com.lfuso.cursos.spring.restful.order.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {


}
