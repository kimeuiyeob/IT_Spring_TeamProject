    package com.app.milestone.domain;

    import com.app.milestone.embeddable.Address;
    import com.app.milestone.embeddable.Introduce;
    import com.app.milestone.entity.School;
    import com.querydsl.core.annotations.QueryProjection;
    import lombok.Data;
    import lombok.NoArgsConstructor;
    import org.springframework.stereotype.Component;

    @Component
    @Data
    @NoArgsConstructor
    public class SchoolDTO {
        private String schoolName;
        private Address address;
        private int schoolTeachers;
        private int schoolKids;
        private int schoolBudget;
        private String schoolBank;
        private String schoolAccount;
        private String schoolPhoneNumber;
        private String schoolQR;
        private Introduce introduce;

        public School toEntity() {
            return School.builder()
                    .schoolName(schoolName)
                    .address(address)
                    .schoolTeachers(schoolTeachers)
                    .schoolKids(schoolKids)
                    .schoolBudget(schoolBudget)
                    .schoolBank(schoolBank)
                    .schoolAccount(schoolAccount)
                    .schoolPhoneNumber(schoolPhoneNumber)
                    .introduce(introduce)
                    .build();
        }

        @QueryProjection
        public SchoolDTO(String schoolName, Address address, int schoolTeachers, int schoolKids, int schoolBudget, String schoolBank, String schoolAccount, String schoolPhoneNumber, String schoolQR, Introduce introduce) {
            this.schoolName = schoolName;
            this.address = address;
            this.schoolTeachers = schoolTeachers;
            this.schoolKids = schoolKids;
            this.schoolBudget = schoolBudget;
            this.schoolBank = schoolBank;
            this.schoolAccount = schoolAccount;
            this.schoolPhoneNumber = schoolPhoneNumber;
            this.schoolQR = schoolQR;
            this.introduce = introduce;
        }
    }
