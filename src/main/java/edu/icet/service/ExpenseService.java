package edu.icet.service;

import edu.icet.model.dto.ExpenseDTO;
import edu.icet.model.entity.ExpenseEntity;
import edu.icet.model.entity.UserEntity;
import edu.icet.model.request.CreateExpenseRequest;
import edu.icet.repository.ExpenseRepository;
import edu.icet.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExpenseService {
    private final ExpenseRepository repo;
    private final UserRepository userRepo;

    public ExpenseDTO createExpense(CreateExpenseRequest req) {

        UserEntity user = userRepo.findById(req.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        ExpenseEntity expense = ExpenseEntity.builder()
                .category(req.getCategory())
                .description(req.getDescription())
                .amount(req.getAmount())
                .date(req.getDate())
                .user(user)
                .build();

        return toDTO(repo.save(expense));
    }

    public List<ExpenseDTO> getAllExpenses() {
        return repo.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public void deleteExpense(Long id) {
        repo.deleteById(id);
    }

    private ExpenseDTO toDTO(ExpenseEntity e) {
        return ExpenseDTO.builder()
                .expenseId(e.getExpenseId())
                .category(e.getCategory())
                .description(e.getDescription())
                .amount(e.getAmount())
                .date(e.getDate())
                .userId(e.getUser().getUserId())
                .userName(e.getUser().getFullName())
                .build();
    }

}
