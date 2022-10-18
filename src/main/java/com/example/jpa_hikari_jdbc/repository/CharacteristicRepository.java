package com.example.jpa_hikari_jdbc.repository;

import com.example.jpa_hikari_jdbc.model.Characteristic;

import com.example.jpa_hikari_jdbc.model.CharacteristicId;
import com.example.jpa_hikari_jdbc.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface CharacteristicRepository extends JpaRepository<Characteristic, CharacteristicId> {
    public Set<Characteristic> findCharacteristicByProduct(Products products);
}
