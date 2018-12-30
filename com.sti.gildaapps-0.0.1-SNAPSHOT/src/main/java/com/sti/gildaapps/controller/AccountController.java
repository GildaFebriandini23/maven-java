package com.sti.gildaapps.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sti.gildaapps.dao.AccountDao;
import com.sti.gildaapps.dao.CustomerDao;
import com.sti.gildaapps.exception.AppsException;
import com.sti.gildaapps.model.Account;
import com.sti.gildaapps.model.Customer;
import com.sti.gildaapps.model.dto.AccountDto;
import com.sti.gildaapps.model.dto.CommonResponse;


@RestController
@RequestMapping("/account") // Base URL 
@SuppressWarnings("rawtypes")
public class AccountController {
	private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AccountDao accountDao;
	
	@Autowired
	private CustomerDao customerDao;
	
	@GetMapping(value="/{x}")
	public CommonResponse getById(@PathVariable("x") String accountNumber) throws AppsException {
		LOGGER.info("accountNumber : {}", accountNumber);
		try {
			Account account = accountDao.getById(Integer.parseInt(accountNumber));
			
			return new CommonResponse<AccountDto>(modelMapper.map(account, AccountDto.class));
		} catch (AppsException e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("01", e.getMessage());
		} catch (NumberFormatException e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", "parameter harus angka");
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());
		}
	}
	
	@PostMapping(value="")
	public CommonResponse insert(@RequestBody AccountDto accountDto) throws AppsException {
		try {
			Account account = modelMapper.map(accountDto, Account.class);
			account.setAccountNumber(0);
			account =  accountDao.save(account);
			
			return new CommonResponse<AccountDto>(modelMapper.map(account, AccountDto.class));
		} catch (AppsException e) {
			return new CommonResponse("01", e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());
		}
	}
	

	@PutMapping(value="")
	public CommonResponse update(@RequestBody AccountDto accountDto) throws AppsException {
		try {
			Account checkAccount = accountDao.getById(accountDto.getAccountNumber());
			if(checkAccount == null) {
				return new CommonResponse("14", "customer tidak ditemukan");
			}
			
			if(accountDto.getOpenDate()!=null) {
				checkAccount.setOpenDate(accountDto.getOpenDate());
			}
			if(accountDto.getBalance()!=null) {
				checkAccount.setBalance(accountDto.getBalance());
			}
			if(accountDto.getCustomer()!=null) {
				checkAccount.setCustomer(modelMapper.map(accountDto.getCustomer(), Customer.class)); //model mapper itu untuk mengonversi
			}
			
			checkAccount =  accountDao.save(checkAccount);
			
			return new CommonResponse<AccountDto>(modelMapper.map(checkAccount, AccountDto.class));
		} catch (AppsException e) {
			return new CommonResponse("01", e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());
		}
	}
	
	@DeleteMapping(value="/{account}")
	public CommonResponse delete(@PathVariable("account") Integer accountNumber) throws AppsException {
		try {
			Account checkAccount = accountDao.getById(accountNumber);
			if(checkAccount == null) {
				return new CommonResponse("06", "account tidak ditemukan");
			}
			accountDao.delete(checkAccount);
			return new CommonResponse();
		} catch (AppsException e) {
			return new CommonResponse("01", e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());
		}
	}
	
	@GetMapping(value="/list")
	public CommonResponse getList(@RequestParam(name="customerNumber", defaultValue="") String customer) throws AppsException{
		try {
			List<Account> accounts;
			if (!StringUtils.isEmpty(customer)) {
				Customer checkCustomer = customerDao.getById(Integer.parseInt(customer));
				if (checkCustomer == null) {
					throw new AppsException("Customer tidak ditemukan !");
				}
				accounts = accountDao.getListByCustomer(checkCustomer);
			} else {
				LOGGER.info("account get list, param : {} ", customer);
				accounts = accountDao.getList();
			}
			
			return new CommonResponse<List<Account>>(accounts.stream().map(acc-> modelMapper.map(acc, Account.class)).collect(Collectors.toList()));
		} catch (AppsException e) {
			throw e;
		} catch(NumberFormatException e) {
			return new CommonResponse("01", e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());
		}
	}


}
