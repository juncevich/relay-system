package com.relay.core.service;

import com.relay.core.exceptions.ShelfNotFoundException;
import com.relay.core.model.storage.Shelf;
import com.relay.db.repository.ShelfRepository;
import com.relay.db.repository.StorageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ShelfService {

    private final ShelfRepository shelfRepository;
    private final StorageRepository storageRepository;

    @Transactional(readOnly = true)
    public @NonNull Page<Shelf> findAll(@NonNull Pageable pageable) {
        return shelfRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public @NonNull Shelf findById(@NonNull Long id) {
        var shelf = shelfRepository.findById(id);
        if (shelf == null) {
            throw new ShelfNotFoundException(id);
        }
        return shelf;
    }

    public Shelf save(@NonNull Shelf model, @NonNull Long storageId) {
        // Validate storage exists
        storageRepository.findStorageEntityById(storageId);

        // Create shelf with storageId
        var shelf = new Shelf(null, model.number(), model.capacity(), storageId);
        return shelfRepository.save(shelf);
    }

    public @NonNull Shelf update(@NonNull Long id, @NonNull Shelf model) {
        var existing = shelfRepository.findById(id);
        if (existing == null) {
            throw new ShelfNotFoundException(id);
        }

        Long storageId = model.storageId() != null ? model.storageId() : existing.storageId();
        if (storageId != null) {
            storageRepository.findStorageEntityById(storageId);
        }

        var updated = new Shelf(id, model.number(), model.capacity(), storageId);
        return shelfRepository.save(updated);
    }

    public void deleteById(@NonNull Long id) {
        shelfRepository.deleteById(id);
    }
}
