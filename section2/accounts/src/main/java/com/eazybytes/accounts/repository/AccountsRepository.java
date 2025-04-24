package com.eazybytes.accounts.repository;

import com.eazybytes.accounts.entity.Accounts;
import com.eazybytes.accounts.entity.Customer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts, Long> {

    Optional<Accounts> findByCustomerId(Long customerId); // este método se usará para buscar una cuenta por su número de cuenta

    @Transactional // este método se usará para eliminar una cuenta por su número de cuenta,por su ocurre un errror , comunicamos a la base de datos
    @Modifying // este método se usará para eliminar una cuenta por su número de cuenta
    void deleteByCustomerId(Long customerId); // este método se usará para eliminar una cuenta por su número de cuenta
  
}
