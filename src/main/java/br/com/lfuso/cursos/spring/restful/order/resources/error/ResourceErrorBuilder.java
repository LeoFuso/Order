package br.com.lfuso.cursos.spring.restful.order.resources.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class ResourceErrorBuilder extends AbstractResourceErrorBuilder<ResourceErrorBuilder, ResourceError> {

	ResourceErrorBuilder(HttpStatus status, String cause) {
		super(status, cause);
	}

	@Override
	ResponseEntity<ResourceError> build() {
		return ResponseEntity
				.status(super.getCode())
				.body(new ResourceError(this));
	}
}
