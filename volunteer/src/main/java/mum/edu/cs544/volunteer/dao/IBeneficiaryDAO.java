package mum.edu.cs544.volunteer.dao;

import java.util.List;

import mum.edu.cs544.volunteer.domain.Beneficiary;

public interface IBeneficiaryDAO {
	void createBeneficiary(Beneficiary beneficiary);
	List<Beneficiary> getAllBeneficiariesByProjectId(int projectId);
}
