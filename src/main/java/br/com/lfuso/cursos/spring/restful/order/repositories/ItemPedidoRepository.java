package br.com.lfuso.cursos.spring.restful.order.repositories;

import br.com.lfuso.cursos.spring.restful.order.domain.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {


}
