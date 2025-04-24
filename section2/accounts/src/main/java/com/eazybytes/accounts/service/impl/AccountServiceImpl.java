package com.eazybytes.accounts.service.impl;

import com.eazybytes.accounts.accounts.AccountsConstants;
import com.eazybytes.accounts.dto.AccountsDto;
import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.entity.Customer;
import com.eazybytes.accounts.entity.Accounts;
import com.eazybytes.accounts.exception.CustomerAlreadyExistsException;
import com.eazybytes.accounts.exception.ResourceNotFoundException;
import com.eazybytes.accounts.mapper.AccounstMapper;
import com.eazybytes.accounts.mapper.CustomerMapper;
import com.eazybytes.accounts.repository.AccountsRepository;
import com.eazybytes.accounts.repository.CustomerRepository;
import com.eazybytes.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountsService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;


     // este método se usará para crear una cuenta, que usa como parámetro un objeto CustomerDto
    @Override
    public void createAccount(CustomerDto customerDto) {
        // Aquí se implementará la lógica para crear una cuenta
        // customrDto se convertirá en un objeto Customer, que es requisito para crear una cuenta
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        Optional <Customer>  optionalCustomer =  customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if (optionalCustomer.isPresent()) {
          throw new CustomerAlreadyExistsException("Customer with mobile number " + customerDto.getMobileNumber() + " already exists");
        }
       /* customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("Anonymous");*/
        Customer savedCustomer  = customerRepository.save(customer);
        accountsRepository.save(createNewAccount(savedCustomer));

    }


    private Accounts createNewAccount(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);
        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
      /*  newAccount.setCreatedAt(LocalDateTime.now());
        newAccount.setCreatedBy("Anonymous");*/
        return newAccount;
    }

    @Override
    public CustomerDto fetchAccount(String mobileNumber) {

        //obtenemos los detalles del cliente a partir del número de teléfono
        Customer customer=  customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow( ()-> new ResourceNotFoundException("Customer","mobileNumber", mobileNumber)
                ); // Aquí se debe implementar la excepción CustomerNotFoundException
        // obtenemos los detalles de la cuenta a partir del id del cliente
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId())
                .orElseThrow(()-> new ResourceNotFoundException("Customer","mobileNumber", customer.getCustomerId().toString()));

        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        customerDto.setAccountsDto(AccounstMapper.mapToAccountsDto(accounts, new AccountsDto()));
        return customerDto;
    }

    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated = false;
        AccountsDto accountsDto = customerDto.getAccountsDto();
        if(accountsDto !=null ){
            Accounts accounts = accountsRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "AccountNumber", accountsDto.getAccountNumber().toString())
            );
            AccounstMapper.mapToAccounts(accountsDto, accounts);
            accounts = accountsRepository.save(accounts);

            Long customerId = accounts.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "CustomerID", customerId.toString())
            );
            CustomerMapper.mapToCustomer(customerDto,customer);
            customerRepository.save(customer);
            isUpdated = true;
        }
        return  isUpdated;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {

        // primero buscamos el cliente por su número de teléfono
        Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)); //si no existe el cliente, lanzamos una excepción
        // con ayuda del repositorio de cuentas, buscamos la cuenta por el id del cliente
        accountsRepository.deleteByCustomerId(customer.getCustomerId());
        // si la cuenta existe, la eliminamos
        customerRepository.deleteById(customer.getCustomerId());
        return true;
    }


}
