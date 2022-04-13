package com.oze.hospital.repository;

import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import com.oze.hospital.entity.Patient;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.concurrent.TimeUnit;

//@SpringBootTest(classes = ProfileRepositoryTest.class)
@SpringBootTest
//@Slf4j
//@ActiveProfiles("test")
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProfileRepositoryTest {

    Faker faker = new Faker();


    @Autowired
    private PatientRepository patientRepository;


    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testData() {
        System.out.println("Begin::");
        long count = patientRepository.count();

        System.out.println("total Count:::" + count);

        System.out.println("End:::");
    }


    @Test
    void generateRandomPatient() throws ParseException {

        for (int i = 0; i < 300; i++) {
            Name name = faker.name();
            int age = faker.number().randomDigitNotZero();

            String pattern1 = "yyyy-MM-dd";
            SimpleDateFormat format1 = new SimpleDateFormat(pattern1);
            Date regastDate = format1.parse("2020-01-01");
            Date registrationDate = faker.date().past(4, TimeUnit.DAYS,regastDate);

            String pattern = "yyyy-MM-dd";
            SimpleDateFormat format = new SimpleDateFormat(pattern);

            Date lastDate = format.parse("2020-01-10");
            Date lastVisitDate = faker.date().future(10, TimeUnit.DAYS,new Date());

            System.out.println("name:::" + name.fullName());
            System.out.println("age:::" + age);
            System.out.println("registrationDate:::" + registrationDate);
            System.out.println("lastVisitDate:::" + lastVisitDate);

            //patientRepository
            Patient patient = new Patient();
            patient.setName(name.fullName());
            patient.setAge(age);
            patient.setLastVisitDate(convertToLocalDateTimeViaInstant(lastVisitDate));
            patient.setCreatedAt(convertToLocalDateTimeViaInstant(registrationDate));

            patientRepository.save(patient);
        }

    }

    public LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }
}
