package io.github.ritter4u.POCGISAPI.Controller

import io.github.ritter4u.POCGISAPI.Domain.Polygon.Entity.PolygonEntity
import io.github.ritter4u.POCGISAPI.Infrastructure.Repository.PolygonEntityRepository
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PagedResourcesAssembler
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import springfox.documentation.annotations.ApiIgnore

@RestController
class PolygonsController(
    private val polygonRepository: PolygonEntityRepository
) {
    @GetMapping("/Polygon/GetAll", produces = ["application/json"])
    fun getPolygonList(
        @ApiIgnore pageable: Pageable,
        @ApiIgnore pagedResourcesAssembler: PagedResourcesAssembler<PolygonEntity>,
    ): ResponseEntity<*> {
        val polygons = polygonRepository.findAll(pageable)
        return ResponseEntity.ok(pagedResourcesAssembler.toModel(polygons))
    }
}
