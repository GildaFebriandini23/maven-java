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
import com.sti.gildaapps.dao.TransactionDao;
import com.sti.gildaapps.exception.AppsException;
import com.sti.gildaapps.model.Transaction;
import com.sti.gildaapps.model.Account;
import com.sti.gildaapps.model.dto.TransactionDto;
import com.sti.gildaapps.model.dto.CommonResponse;


@RestController
@RequestMapping("/transaction") // Base URL 
@SuppressWarnings("rawtypes")
public class TransactionController {
	private static final Logger LOGGER = LoggerFactory.getLogger(TransactionController.class);
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private TransactionDao transactionDao;
	
	@Autowired
	private AccountDao accountDao;
	
	@GetMapping(value="/{x}")
	public CommonResponse getById(@PathVariable("x") String id) throws AppsException {
		LOGGER.info("id : {}", id);
		try {
			Transaction transaction = transactionDao.getById(Integer.parseInt(id));
			
			return new CommonResponse<TransactionDto>(modelMapper.map(transaction, TransactionDto.class));
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
	public CommonResponse insert(@RequestBody TransactionDto transactionDto) throws AppsException {
		try {
			Transaction transaction = modelMapper.map(transactionDto, Transaction.class);
			transaction.setId(0);
			transaction = transactionDao.save(transaction);
			
			return new CommonResponse<TransactionDto>(modelMapper.map(transaction, TransactionDto.class));
		} catch (AppsException e) {
			return new CommonResponse("01", e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());
		}
	}
	

	@PutMapping(value="")
	public CommonResponse update(@RequestBody TransactionDto transactionDto) throws AppsException {
		try {
			Transaction checkTransaction = transactionDao.getById(transactionDto.getId());
			if(checkTransaction == null) {
				return new CommonResponse("14", "Transaction tidak ditemukan");
			}
			
			if(transactionDto.getType()!=null) {
				checkTransaction.setType(transactionDto.getType());
			}
			if(transactionDto.getAmount()!=null) {
				checkTransaction.setAmount(transactionDto.getAmount());
			}
			if(transactionDto.getAmountSign()!=null) {
				checkTransaction.setAmountSign(transactionDto.getAmountSign());
			}
			if(transactionDto.getAccount()!=null) {
				checkTransaction.setAccount(modelMapper.map(transactionDto.getAccount(), Account.class)); //model mapper itu untuk mengonversi
			}
			
			checkTransaction =  transactionDao.save(checkTransaction);
			
			return new CommonResponse<TransactionDto>(modelMapper.map(checkTransaction, TransactionDto.class));
		} catch (AppsException e) {
			return new CommonResponse("01", e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());
		}
	}
	
	@DeleteMapping(value="/{transaction}")
	public CommonResponse delete(@PathVariable("transaction") Integer id) throws AppsException {
		try {
			Transaction checkTransaction = transactionDao.getById(id);
			if(checkTransaction == null) {
				return new CommonResponse("06", "Transaction tidak ditemukan");
			}
			transactionDao.delete(checkTransaction);
			return new CommonResponse();
		} catch (AppsException e) {
			return new CommonResponse("01", e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());
		}
	}
	
	@GetMapping(value="/list")
	public CommonResponse getList(@RequestParam(name="accountNumber", defaultValue="") String account) throws AppsException{
		try {
			List<Transaction> transactions;
			if (!StringUtils.isEmpty(account)) {
				Account checkAccount = accountDao.getById(Integer.parseInt(account));
				if (checkAccount == null) {
					throw new AppsException("Customer tidak ditemukan !");
				}
				transactions = transactionDao.getListByAccount(checkAccount);
			} else {
				LOGGER.info("account get list, param : {} ", account);
				transactions = transactionDao.getList();
			}
			
			return new CommonResponse<List<Transaction>>(transactions.stream().map(trx -> modelMapper.map(trx, Transaction.class)).collect(Collectors.toList()));
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
