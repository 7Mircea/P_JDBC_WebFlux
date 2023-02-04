package com.example.jpa_hikari_jdbc.repository;

import com.example.jpa_hikari_jdbc.model.Characteristic;

import com.example.jpa_hikari_jdbc.model.CharacteristicId;
import com.example.jpa_hikari_jdbc.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface CharacteristicRepository extends JpaRepository<Characteristic, CharacteristicId> {
    @Query(value = "select * from characteristic where id_prod=:idProd", nativeQuery = true)
    public Set<Characteristic> findCharacteristicByProduct(int idProd);
}
