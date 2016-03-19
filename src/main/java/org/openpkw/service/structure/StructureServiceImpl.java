package org.openpkw.service.structure;

import au.com.bytecode.opencsv.CSVReader;
import org.openpkw.model.entity.*;
import org.openpkw.model.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Remigiusz.Mrozek on 2016-03-19.
 */
public class StructureServiceImpl implements StructureService {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private DistrictCommitteeRepository districtCommitteeRepository;

    @Autowired
    private ElectionCommitteeRepository electionCommitteeRepository;

    @Autowired
    private ElectionCommitteeDistrictRepository electionCommitteeDistrictRepository;

    @Autowired
    private PeripheralCommitteeRepository peripheralCommitteeRepository;

    public static final String CHARSET_NAME = "UTF-8";
    private final static String FILE_NAME_DISTRICTS = "/districts.csv";
    private final static String FILE_NAME_PERIPHERALS = "/peripherals.csv";
    private final static String FILE_NAME_CANDIDATES = "/candidates.csv";
    private List<DistrictCommittee> districtCommitteeList;
    private List<PeripheralCommittee> peripheralCommitteeList;
    private List<Candidate> candidateList;
    private List<ElectionCommittee> electionCommitteeList;
    private List<ElectionCommitteeDistrict> electionCommitteeDistrictList;


    private static String getStringFromCsv(List<String[]> listAllFieldInFile, int line, int column) {
        return listAllFieldInFile.get(line)[column].trim();
    }

    private static Integer getIntFromCsv(List<String[]> listAllFieldInFile, int line, int column) {
        return Integer.parseInt(listAllFieldInFile.get(line)[column]);
    }

    private PeripheralCommittee getPeripheralCommitte(int line, List<String[]> listAllFieldInFile, List<DistrictCommittee> districtCommitteeList) {
        PeripheralCommittee peripheralCommittee = new PeripheralCommittee();
        peripheralCommittee.setAddress(getStringFromCsv(listAllFieldInFile, line, PeripheralCsvLine.AddressName.getLineNumber()));
        peripheralCommittee.setNumber(getIntFromCsv(listAllFieldInFile, line, PeripheralCsvLine.Number.getLineNumber()));
        peripheralCommittee.setTerritorialCode(getStringFromCsv(listAllFieldInFile, line, PeripheralCsvLine.TeritorialCode.getLineNumber()));
        peripheralCommittee.setName(getStringFromCsv(listAllFieldInFile, line, PeripheralCsvLine.Name.getLineNumber()));

        int committeeNumber = getIntFromCsv(listAllFieldInFile, line, PeripheralCsvLine.DistrictNumber.getLineNumber());
        DistrictCommittee districtCommittee = districtCommitteeList.stream().filter(a -> a.getNumber() == committeeNumber).findFirst().get();

        peripheralCommittee.setDistrictCommittee(districtCommittee);
        return peripheralCommittee;
    }

    private DistrictCommittee getDistrictCommittee(int line, List<String[]> listAllFieldInFile) {

        DistrictCommittee districtCommittee = new DistrictCommittee();
        districtCommittee.setAddress(getStringFromCsv(listAllFieldInFile, line, DistrictCsvLine.City.getLineNumber()));
        districtCommittee.setNumber(getIntFromCsv(listAllFieldInFile, line, DistrictCsvLine.Number.getLineNumber()));
        districtCommittee.setName(getStringFromCsv(listAllFieldInFile, line, DistrictCsvLine.Name.getLineNumber()));
        return districtCommittee;
    }

    private void readPeripheralCommiteeFromCsv(String fileName) {
        peripheralCommitteeList = new ArrayList<PeripheralCommittee>();
        CSVReader reader = null;
        try {

            reader = new CSVReader(new InputStreamReader(this.getClass().getResourceAsStream(fileName), CHARSET_NAME), ',', '\"');

            List<String[]> listAllFieldInFile = reader.readAll();
            for (int i = 0; i < listAllFieldInFile.size(); i++) {
                peripheralCommitteeList.add(getPeripheralCommitte(i, listAllFieldInFile, districtCommitteeList));
            }
        } catch (Exception ex) {
            throw new RuntimeException("Failed to parse file " + fileName + ": " + ex.getMessage(), ex);

        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (Exception ex) {

            }
        }
    }

    private Candidate getCandidate(int line, List<String[]> listAllFieldInFile, List<DistrictCommittee> districtCommitteeList) {
        ElectionCommittee electionCommittee = new ElectionCommittee();
        electionCommittee.setName(getStringFromCsv(listAllFieldInFile, line, CandidateCsvLine.ElectionCommiteeListName.getLineNumber()));

        ElectionCommitteeDistrict electionCommitteeDistrict = new ElectionCommitteeDistrict();
        electionCommitteeDistrict.setElectionCommitteeId(electionCommittee);
        electionCommitteeDistrict.setListNumber(getIntFromCsv(listAllFieldInFile, line, CandidateCsvLine.ElectionCommitteeListNumber.getLineNumber()));
        int committeeNumber = getIntFromCsv(listAllFieldInFile, line, CandidateCsvLine.DistrictNumber.getLineNumber());
        DistrictCommittee districtCommittee = districtCommitteeList.stream().filter(a -> a.getNumber() == committeeNumber).findFirst().get();
        electionCommitteeDistrict.setDistrictCommittee(districtCommittee);

        Candidate candidate = new Candidate();
        candidate.setElectionCommitteeDistrict(electionCommitteeDistrict);
        candidate.setName(getStringFromCsv(listAllFieldInFile, line, CandidateCsvLine.Name.getLineNumber()));
        candidate.setSurname(getStringFromCsv(listAllFieldInFile, line, CandidateCsvLine.Surname.getLineNumber()));
        candidate.setPositionOnList(getIntFromCsv(listAllFieldInFile, line, CandidateCsvLine.Number.getLineNumber()));

        return candidate;
    }

    private void extractCandidateList() {

        electionCommitteeList = new ArrayList<ElectionCommittee>();
        electionCommitteeDistrictList = new ArrayList<ElectionCommitteeDistrict>();
        for (Candidate candidate : candidateList) {
            Optional<ElectionCommitteeDistrict> findDistrict = electionCommitteeDistrictList.stream().filter(a -> a.getDistrictCommittee().getNumber() == candidate.getElectionCommitteeDistrict().getDistrictCommittee().getNumber() && a.getListNumber() == candidate.getElectionCommitteeDistrict().getListNumber()).findFirst();

            if (findDistrict.isPresent()) {
                candidate.setElectionCommitteeDistrict(findDistrict.get());
            } else {
                Optional<ElectionCommittee> find = electionCommitteeList.stream().filter(a -> a.getName().trim().equals(candidate.getElectionCommitteeDistrict().getElectionCommitteeId().getName().trim())).findFirst();
                if (find.isPresent()) {
                    electionCommitteeDistrictList.add(candidate.getElectionCommitteeDistrict());
                    candidate.getElectionCommitteeDistrict().setElectionCommitteeId(find.get());
                } else {
                    electionCommitteeList.add(candidate.getElectionCommitteeDistrict().getElectionCommitteeId());
                    electionCommitteeDistrictList.add(candidate.getElectionCommitteeDistrict());
                }
            }
        }
      }

    private void readCandidateListFromCsv(String fileName) {

        candidateList = new ArrayList<Candidate>();
        CSVReader reader = null;
        try {

            reader = new CSVReader(new InputStreamReader(this.getClass().getResourceAsStream(fileName), CHARSET_NAME), ',', '\"');
            List<String[]> listAllFieldInFile = reader.readAll();
            for (int i = 0; i < listAllFieldInFile.size(); i++) {
                candidateList.add(getCandidate(i, listAllFieldInFile, districtCommitteeList));
            }
        } catch (Exception ex) {
            throw new RuntimeException("Failed to parse file " + fileName + ": " + ex.getMessage(), ex);
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (Exception ex) {

            }
        }

    }

    private void readDistrictCommitteeListFromCsv(String fileName) {

        CSVReader reader = null;
        districtCommitteeList = new ArrayList<DistrictCommittee>();
        try {

            reader = new CSVReader(new InputStreamReader(this.getClass().getResourceAsStream(fileName), CHARSET_NAME), ',', '\"');
            List<String[]> listAllFieldInFile = reader.readAll();
            for (int i = 0; i < listAllFieldInFile.size(); i++) {
                districtCommitteeList.add(getDistrictCommittee(i, listAllFieldInFile));
            }
        } catch (Exception ex) {
            throw new RuntimeException("Failed to parse file " + fileName + ": " + ex.getMessage(), ex);
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (Exception ex) {

            }
        }

    }

    private void deleteDatabase() {

        candidateRepository.deleteAllInBatch();

        peripheralCommitteeRepository.deleteAllInBatch();

        electionCommitteeDistrictRepository.deleteAllInBatch();
        electionCommitteeRepository.deleteAllInBatch();
        districtCommitteeRepository.deleteAllInBatch();

    }
    private void readCsvFile()
    {
        readDistrictCommitteeListFromCsv(FILE_NAME_DISTRICTS);
        readPeripheralCommiteeFromCsv(FILE_NAME_PERIPHERALS);
        readCandidateListFromCsv(FILE_NAME_CANDIDATES);
        extractCandidateList();
    }

    public void writeToDatabase() {

        writeToDatabaseDistrictCommitteeAndAddress();
        writeToDatabaseDistrictPeripheralCommitteeAndAdress();
        writeToDatabaseElectionCommittee();
        writeToDatabaseElectionDistrictCommittee();
        writeToDatabaseCandidate();


    }

    private void writeToDatabaseDistrictCommitteeAndAddress() {
        for (DistrictCommittee districtCommittee : this.districtCommitteeList) {
            districtCommitteeRepository.save(districtCommittee);


        }
    }

    private void writeToDatabaseDistrictPeripheralCommitteeAndAdress() {

        for (PeripheralCommittee peripheralCommittee : this.peripheralCommitteeList) {
            peripheralCommitteeRepository.save(peripheralCommittee);
        }
    }

    private void writeToDatabaseElectionCommittee() {
        for (ElectionCommittee electionCommittee : this.electionCommitteeList) {
            electionCommitteeRepository.save(electionCommittee);
        }
    }

    private void writeToDatabaseElectionDistrictCommittee() {
        for (ElectionCommitteeDistrict electionCommitteeDistrict : this.electionCommitteeDistrictList) {
            electionCommitteeDistrictRepository.save(electionCommitteeDistrict);
        }
    }

    private void writeToDatabaseCandidate() {
        for (Candidate candidate : this.candidateList) {
            candidateRepository.save(candidate);
        }
    }



    @Override
    public void initStructureService() throws Exception {
        deleteDatabase();
        readCsvFile();
        writeToDatabase();
    }
}
