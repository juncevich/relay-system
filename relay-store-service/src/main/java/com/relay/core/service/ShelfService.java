package com.relay.core.service;

import com.relay.core.model.storage.Shelf;
import com.relay.db.mappers.ShelfMapper;
import com.relay.db.repository.ShelfRepository;
import com.relay.db.repository.StorageRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ShelfService {

    private final ShelfRepository shelfRepository;
    private final StorageRepository storageRepository;
    private final ShelfMapper shelfMapper;

    public @NonNull List<Shelf> findAll(@NonNull Pageable pageable) {
        var slice = shelfRepository.findAll(pageable);
        return slice.hasContent()
                ? slice.getContent().stream().map(shelfMapper::mapEntityToModel).toList()
                : List.of();
    }

    public @Nullable Shelf findById(@NonNull Long id) {
        return shelfRepository.findById(id)
                .map(shelfMapper::mapEntityToModel)
                .orElse(null);
    }

    public Shelf save(@NonNull Shelf model, @NonNull Long storageId) {
        com.relay.db.entity.storage.Storage storage = storageRepository.findById(storageId)
                .orElseThrow(() -> new IllegalArgumentException("Storage not found: " + storageId));
        com.relay.db.entity.storage.Shelf entity = shelfMapper.mapModelToEntity(model);
        entity.setStorage(storage);
        com.relay.db.entity.storage.Shelf saved = shelfRepository.save(entity);
        return shelfMapper.mapEntityToModel(saved);
    }

    public @Nullable Shelf update(@NonNull Long id, @NonNull Shelf model) {
        return shelfRepository.findById(id)
                .map(shelf -> {
                    shelf.setNumber(model.number());
                    shelf.setCapacity(model.capacity());
                    if (model.storageId() != null) {
                        com.relay.db.entity.storage.Storage storage = storageRepository.findById(model.storageId())
                                .orElseThrow(() -> new IllegalArgumentException("Storage not found: " + model.storageId()));
                        shelf.setStorage(storage);
                    }
                    return shelfMapper.mapEntityToModel(shelfRepository.save(shelf));
                })
                .orElse(null);
    }

    public void deleteById(@NonNull Long id) {
        shelfRepository.deleteById(id);
    }
}