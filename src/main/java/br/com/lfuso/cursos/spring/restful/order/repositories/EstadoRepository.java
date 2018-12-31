package br.com.lfuso.cursos.spring.restful.order.repositories;

import br.com.lfuso.cursos.spring.restful.order.domain.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {


}
