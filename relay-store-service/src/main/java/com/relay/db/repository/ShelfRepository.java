package com.relay.db.repository;

import com.relay.core.model.storage.Shelf;
import com.relay.db.dao.ShelfDao;
import com.relay.db.mappers.ShelfMapper;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ShelfRepository {

    private final ShelfDao shelfDao;
    private final ShelfMapper shelfMapper;

    public @NonNull List<Shelf> findAll(@NonNull Pageable pageable) {
        var slice = shelfDao.findAll(pageable);
        return slice.hasContent()
                ? shelfMapper.mapEntityToModel(slice.getContent())
                : List.of();
    }

    public @Nullable Shelf findById(@NonNull Long id) {
        return shelfDao.findById(id)
                .map(shelfMapper::mapEntityToModel)
                .orElse(null);
    }

    public Shelf save(@NonNull Shelf model) {
        var entity = shelfMapper.mapModelToEntity(model);
        var saved = shelfDao.save(entity);
        return shelfMapper.mapEntityToModel(saved);
    }

    public void deleteById(@NonNull Long id) {
        shelfDao.deleteById(id);
    }

    // Helper method to get entity for relationship wiring
    public com.relay.db.entity.storage.@NonNull Shelf findShelfEntityById(@NonNull Long shelfId) {
        return shelfDao.findById(shelfId)
                .orElseThrow(() -> new IllegalArgumentException("Shelf not found: " + shelfId));
    }
}
