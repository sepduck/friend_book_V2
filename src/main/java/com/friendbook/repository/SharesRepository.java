package com.friendbook.repository;

import com.friendbook.model.Shares;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SharesRepository extends JpaRepository<Shares, Long> {
}
