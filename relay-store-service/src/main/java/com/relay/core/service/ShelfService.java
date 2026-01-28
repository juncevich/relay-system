package com.relay.core.service;

import com.relay.core.model.storage.ShelfModel;
import com.relay.db.entity.storage.Shelf;
import com.relay.db.entity.storage.Storage;
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

    public @NonNull List<ShelfModel> findAll(@NonNull Pageable pageable) {
        var slice = shelfRepository.findAll(pageable);
        return slice.hasContent()
                ? slice.getContent().stream().map(shelfMapper::mapEntityToModel).toList()
                : List.of();
    }

    public @Nullable ShelfModel findById(@NonNull Long id) {
        return shelfRepository.findById(id)
                .map(shelfMapper::mapEntityToModel)
                .orElse(null);
    }

    public ShelfModel save(@NonNull ShelfModel model, @NonNull Long storageId) {
        Storage storage = storageRepository.findById(storageId)
                .orElseThrow(() -> new IllegalArgumentException("Storage not found: " + storageId));
        Shelf entity = shelfMapper.mapModelToEntity(model);
        entity.setStorage(storage);
        Shelf saved = shelfRepository.save(entity);
        return shelfMapper.mapEntityToModel(saved);
    }

    public @Nullable ShelfModel update(@NonNull Long id, @NonNull ShelfModel model) {
        return shelfRepository.findById(id)
                .map(shelf -> {
                    shelf.setNumber(model.number());
                    shelf.setCapacity(model.capacity());
                    if (model.storageId() != null) {
                        Storage storage = storageRepository.findById(model.storageId())
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