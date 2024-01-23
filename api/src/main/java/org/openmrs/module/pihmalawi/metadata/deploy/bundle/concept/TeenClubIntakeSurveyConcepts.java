package org.openmrs.module.pihmalawi.metadata.deploy.bundle.concept;

import org.openmrs.Concept;
import org.openmrs.api.ConceptNameType;
import org.openmrs.module.metadatadeploy.MetadataUtils;
import org.openmrs.module.metadatadeploy.builder.ConceptBuilder;
import org.openmrs.module.metadatadeploy.bundle.Requires;
import org.openmrs.module.pihmalawi.metadata.deploy.bundle.VersionedPihConceptBundle;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@Requires({CoreConceptMetadataBundle.class})
public class TeenClubIntakeSurveyConcepts extends VersionedPihConceptBundle {

    @Override
    public int getVersion() {
        return 95;
    }
    @Override
    protected void installNewVersion() throws Exception {

        Concept yes = MetadataUtils.existing(Concept.class, CommonConcepts.Concepts.YES);
        Concept no = MetadataUtils.existing(Concept.class, CommonConcepts.Concepts.NO);
        Concept other = MetadataUtils.existing(Concept.class, CommonConcepts.Concepts.OTHER);

        Concept interviewerName = install(new ConceptBuilder("9562d3b9-11cf-4522-a062-e9aaa94f38b4")
                .datatype(text)
                .conceptClass(misc)
                .name("a854433a-00fb-46bd-b652-785d416eac63", "Interviewer Name", Locale.ENGLISH, ConceptNameType.FULLY_SPECIFIED)
                .build());

        Concept getaJob = install(new ConceptBuilder("b0cccb19-5744-4d7c-a20f-5357d8eedfa8")
                .datatype(text)
                .conceptClass(misc)
                .name("193c465b-abb1-4dd6-97eb-7edd5fca16de", "Get a job", Locale.ENGLISH, ConceptNameType.FULLY_SPECIFIED)
                .build());

        Concept business = install(new ConceptBuilder("3b7d62ef-c044-4cfa-bf44-ba3fcdea70ee")
                .datatype(text)
                .conceptClass(misc)
                .name("17ec4545-1c48-4456-a567-5f04692d28bc", "Business", Locale.ENGLISH, ConceptNameType.FULLY_SPECIFIED)
                .build());

        Concept furtherEducation = install(new ConceptBuilder("534c8fba-8077-445a-a2dd-ab90d5af8090")
                .datatype(text)
                .conceptClass(misc)
                .name("dcd182e6-38e6-4675-851b-21d7950a8fb5", "Further Education", Locale.ENGLISH, ConceptNameType.FULLY_SPECIFIED)
                .build());

        Concept farming = install(new ConceptBuilder("aac9cf84-ebe7-41c1-b2e9-06514af2c1f8")
                .datatype(text)
                .conceptClass(misc)
                .name("b6bee996-dbff-403b-bb7b-f41eef535d88", "Farming", Locale.ENGLISH, ConceptNameType.FULLY_SPECIFIED)
                .build());
        Concept plansAfterSchool = install(new ConceptBuilder("e1eef37b-98f5-4c86-9560-c090e5b1731f")
                .datatype(coded)
                .conceptClass(misc)
                .name("21b809cb-4295-4df7-b96a-7bc6dedd2482", "Plans after school", Locale.ENGLISH, ConceptNameType.FULLY_SPECIFIED)
                .answers(getaJob, business, furtherEducation, other)
                .build());

        Concept biologicalParent = install(new ConceptBuilder("ce0af2a6-d6f4-4d13-8af3-ea4ad378a653")
                .datatype(notApplicable)
                .conceptClass(misc)
                .name("17f5435b-700f-4fba-9070-077edb124636", "Biological Parent(Single)", Locale.ENGLISH, ConceptNameType.FULLY_SPECIFIED)
                .build());

        Concept biologicalParents = install(new ConceptBuilder("49e133bf-efc9-49f9-91af-1cc26fa88ec6")
                .datatype(notApplicable)
                .conceptClass(misc)
                .name("9e98b982-37d1-41e1-b0f7-b78714a4ae36", "Biological Parents-(both)", Locale.ENGLISH, ConceptNameType.FULLY_SPECIFIED)
                .build());

        Concept grandparents = install(new ConceptBuilder("aa109c28-7745-480e-a3c2-d0bc9f1a2719")
                .datatype(notApplicable)
                .conceptClass(misc)
                .name("8bbcbb11-fa1d-4713-aa68-58f296cb92ff", "Grandparents", Locale.ENGLISH, ConceptNameType.FULLY_SPECIFIED)
                .build());

        Concept elderRelative = install(new ConceptBuilder("d0c7636d-9d5e-4c1c-9656-e76689398ce5")
                .datatype(notApplicable)
                .conceptClass(misc)
                .name("0b16179a-3add-4555-94bb-aa129d75be3b", "Elder relative", Locale.ENGLISH, ConceptNameType.FULLY_SPECIFIED)
                .build());

        Concept notRelative = install(new ConceptBuilder("dadec55d-864d-4f33-bfc2-06fccccd6a2a")
                .datatype(notApplicable)
                .conceptClass(misc)
                .name("ada90dce-79af-4322-b1b1-c13907f9b9d3", "Non-Relative (neighbor,friend,community person)", Locale.ENGLISH, ConceptNameType.FULLY_SPECIFIED)
                .build());

        Concept primaryGuardian = install(new ConceptBuilder("807bcfed-6fb9-4ca3-bb46-8286a51906a2")
                .datatype(coded)
                .conceptClass(misc)
                .name("55b1db3e-b0d7-4a1f-a045-a247432232dd", "Primary Guardian", Locale.ENGLISH, ConceptNameType.FULLY_SPECIFIED)
                .answers(biologicalParent,biologicalParents,grandparents,elderRelative,notRelative,other)
                .build());

        Concept location = install(new ConceptBuilder("b7d8d419-2451-44ec-9b6b-9b855f9cc443")
                .datatype(text)
                .conceptClass(misc)
                .name("ea48311c-aa4f-48c2-a3a4-901b3ae9be6b", "Parents location", Locale.ENGLISH, ConceptNameType.FULLY_SPECIFIED)
                .build());

        Concept parentsStatus = install(new ConceptBuilder("eed747bb-d4c1-40b0-89e1-f7dd30a80cec")
                .datatype(text)
                .conceptClass(misc)
                .name("62efec4f-69c5-4b48-b8d2-9efce85fa565", "Parents Status", Locale.ENGLISH, ConceptNameType.FULLY_SPECIFIED)
                .build());

        Concept divorce = install(new ConceptBuilder("bf061dbe-7ead-43a9-a8db-a6869b2daea1")
                .datatype(text)
                .conceptClass(misc)
                .name("ea720df9-df40-4b64-99aa-6f89113a1f7e", "Parents divorced", Locale.ENGLISH, ConceptNameType.FULLY_SPECIFIED)
                .build());

        Concept died = install(new ConceptBuilder("c4c3dd23-48a1-4f24-9415-8865ef45b9e0")
                .datatype(text)
                .conceptClass(misc)
                .name("14cc0668-d5e5-452c-9940-33d641cb0753", "Parents died", Locale.ENGLISH, ConceptNameType.FULLY_SPECIFIED)
                .build());

        Concept childStaysWithSingleParent = install(new ConceptBuilder("0b6a3722-c4d3-44ba-afd4-6185eba83849")
                .datatype(coded)
                .conceptClass(misc)
                .name("3c0fdeaa-3519-4a0d-bb9f-8ee8b907f20c", "Reason for staying with single parent", Locale.ENGLISH, ConceptNameType.FULLY_SPECIFIED)
                .answers(divorce, died, other)
                .build());

        Concept numberOfPeopleInHousehold = install(new ConceptBuilder("441ed2b2-623a-42a0-aa7b-d16327cfc697")
                .datatype(numeric)
                .conceptClass(misc)
                .name("36127940-869d-4ab5-b957-3d671c6bd54b", "Total number of household members", Locale.ENGLISH, ConceptNameType.FULLY_SPECIFIED)
                .build());

//        Concept radio = install(new ConceptBuilder("fc3b7ba7-11de-4117-88f3-495b25160c50")
//                .datatype(notApplicable)
//                .conceptClass(misc)
//                .name("3aa0982b-6b5a-4467-975e-d765604877a8", "Radio", Locale.ENGLISH, ConceptNameType.FULLY_SPECIFIED)
//                .build());

        Concept television = install(new ConceptBuilder("42b9b102-3af4-4fc0-8459-34518de75d74")
                .datatype(notApplicable)
                .conceptClass(misc)
                .name("3874a8ec-2d9e-443c-a51c-ed3fa40364d7", "Television", Locale.ENGLISH, ConceptNameType.FULLY_SPECIFIED)
                .build());

//        Concept mobileTelephone = install(new ConceptBuilder("a91430f1-1b39-4d5a-9321-79855cb9cc31")
//                .datatype(notApplicable)
//                .conceptClass(misc)
//                .name("39e315bd-c1a6-46cc-aff2-3984d10a8484", "Mobile Telephone", Locale.ENGLISH, ConceptNameType.FULLY_SPECIFIED)
//                .build());
//
        Concept refrigerator = install(new ConceptBuilder("d0b2da00-a67b-4d23-8c70-b8114fb66cb0")
                .datatype(notApplicable)
                .conceptClass(misc)
                .name("b7b2df66-a8b1-4fda-abdb-c6fa6af32434", "Refrigerator", Locale.ENGLISH, ConceptNameType.FULLY_SPECIFIED)
                .build());
//
//        Concept bicycle = install(new ConceptBuilder("2bbaf89f-1f19-43f8-9d42-25f9f3f496cd")
//                .datatype(notApplicable)
//                .conceptClass(misc)
//                .name("282ed2fa-dd4a-494a-a856-2853fbdfd7b6", "Bicycle", Locale.ENGLISH, ConceptNameType.FULLY_SPECIFIED)
//                .build());
//
        Concept motorcycle = install(new ConceptBuilder("0f42c300-677e-4483-b0ab-9c9542c7dba2")
                .datatype(notApplicable)
                .conceptClass(misc)
                .name("c26d0b56-dae1-4189-8e3c-09a1a5e805a3", "Motorcycle", Locale.ENGLISH, ConceptNameType.FULLY_SPECIFIED)
                .build());
//
        Concept animalDrawnCart = install(new ConceptBuilder("f3f934a2-174c-45a0-84e1-17d31e9e548d")
                .datatype(notApplicable)
                .conceptClass(misc)
                .name("bcd81973-f36f-46bf-8328-819a043aa0b0", "Animal-drawn cart", Locale.ENGLISH, ConceptNameType.FULLY_SPECIFIED)
                .build());
//
//        Concept car = install(new ConceptBuilder("8705ba42-34e3-4ae0-90aa-e30508fd6e3a")
//                .datatype(notApplicable)
//                .conceptClass(misc)
//                .name("cd93001b-a145-425d-b218-aac223e8ab39", "Car/Truck", Locale.ENGLISH, ConceptNameType.FULLY_SPECIFIED)
//                .build());
//
        Concept electricity = install(new ConceptBuilder("4f3d9103-a1d2-40cb-953b-9aeeb9950147")
                .datatype(notApplicable)
                .conceptClass(misc)
                .name("46080c96-af7d-413e-bf31-432f377eaf93", "Electricity", Locale.ENGLISH, ConceptNameType.FULLY_SPECIFIED)
                .build());
//
        Concept solar = install(new ConceptBuilder("8f8f610c-5db5-4631-85d8-4e4c413e65c5")
                .datatype(notApplicable)
                .conceptClass(misc)
                .name("7814af8f-e465-416a-8c77-63fc3dafdd43", "Solar", Locale.ENGLISH, ConceptNameType.FULLY_SPECIFIED)
                .build());
//
        Concept gas = install(new ConceptBuilder("03fb4666-62b6-4ae3-a086-f6c0e6d9b9c3")
                .datatype(notApplicable)
                .conceptClass(misc)
                .name("176eb891-580c-4d32-bea6-a05c91a29ff1", "From gas", Locale.ENGLISH, ConceptNameType.FULLY_SPECIFIED)
                .build());
//
        Concept paraffinLamp = install(new ConceptBuilder("1ecdd3d4-3dbc-4e50-a17a-d64f84ed9886")
                .datatype(notApplicable)
                .conceptClass(misc)
                .name("8d79afc4-80cc-48f1-8609-5093095d9309", "Paraffin Lamp", Locale.ENGLISH, ConceptNameType.FULLY_SPECIFIED)
                .build());
//
        Concept torch = install(new ConceptBuilder("499d2f21-86e8-4e81-93fa-f44b3c3e09b6")
                .datatype(notApplicable)
                .conceptClass(misc)
                .name("f778dc78-645d-46e0-8ed4-19b6470542c2", "Torch", Locale.ENGLISH, ConceptNameType.FULLY_SPECIFIED)
                .build());
//
        Concept pipedWater = install(new ConceptBuilder("41b2529c-e8d9-4f28-92cd-4b7847c23f5e")
                .datatype(notApplicable)
                .conceptClass(misc)
                .name("ff9fe2b3-91c3-42f3-9d61-408179144fc9", "Piped Water", Locale.ENGLISH, ConceptNameType.FULLY_SPECIFIED)
                .build());
//
//        Concept dugWell = install(new ConceptBuilder("ae3a12b4-2c25-4edb-9010-6bd4857c0795")
//                .datatype(notApplicable)
//                .conceptClass(misc)
//                .name("1472376a-08b6-4fb1-bac1-62e2d930a52f", "Gug Well", Locale.ENGLISH, ConceptNameType.FULLY_SPECIFIED)
//                .build());
        Concept fromWell = install(new ConceptBuilder("d9ba6164-60ca-4540-a080-01b4c24f64d2")
                .datatype(notApplicable)
                .conceptClass(misc)
                .name("24745e1e-a028-4ff1-83c1-ad8fc01ca904", "Dug Well", Locale.ENGLISH, ConceptNameType.FULLY_SPECIFIED)
                .build());
        ////
        Concept spring = install(new ConceptBuilder("2d901246-1c15-495c-8102-02b3a7fbfbc3")
                .datatype(notApplicable)
                .conceptClass(misc)
                .name("e59837f5-4d19-44a6-8968-005544f84062", "Water from spring", Locale.ENGLISH, ConceptNameType.FULLY_SPECIFIED)
                .build());
////
        Concept bottledWater = install(new ConceptBuilder("0db5816d-8349-400d-a617-013f1e9658f7")
                .datatype(notApplicable)
                .conceptClass(misc)
                .name("deb51bd5-6bce-4053-81e6-9aa731f07b7b", "Bottled Water", Locale.ENGLISH, ConceptNameType.FULLY_SPECIFIED)
                .build());
//
        Concept borehole = install(new ConceptBuilder("c07c34ad-08e7-469c-a3d5-3c93f46ff5f8")
                .datatype(notApplicable)
                .conceptClass(misc)
                .name("831e938f-fb8e-4ede-a15c-b1ba841a394c", "Water from Borehole", Locale.ENGLISH, ConceptNameType.FULLY_SPECIFIED)
                .build());
////
        Concept toiletAtHome = install(new ConceptBuilder("9bcdf631-c727-48e7-98fd-795fcd544d32")
                .datatype(coded)
                .conceptClass(misc)
                .name("d1291b0e-d670-4ebb-b900-aa7b04b2c99e", "Do you have a toilet at home?", Locale.ENGLISH, ConceptNameType.FULLY_SPECIFIED)
                .answers(yes, no)
                .build());
//
        Concept flushToilet = install(new ConceptBuilder("219fa3bd-df79-456c-a188-3b57cbe9440e")
                .datatype(notApplicable)
                .conceptClass(misc)
                .name("31c18b4e-7598-42b6-895c-aa6668819651", "Flush Toilet", Locale.ENGLISH, ConceptNameType.FULLY_SPECIFIED)
                .build());
////
        Concept pitLatrine = install(new ConceptBuilder("76a54a76-5a16-4767-9655-d3e1f0f6f13f")
                .datatype(notApplicable)
                .conceptClass(misc)
                .name("6cce6b2b-36d2-4e01-9c2d-2c825bfee75e", "Pit Latrine", Locale.ENGLISH, ConceptNameType.FULLY_SPECIFIED)
                .build());
//
        Concept kindOfToilet = install(new ConceptBuilder("353df499-ffb9-4ca5-a63a-0ec6fb029e94")
                .datatype(coded)
                .conceptClass(misc)
                .name("60592ce9-e129-4471-8c78-1afd748e6078", "Kind of toilet facility used by household members", Locale.ENGLISH, ConceptNameType.FULLY_SPECIFIED)
                .answers(flushToilet,pitLatrine,other)
                .build());

        Concept firewood = install(new ConceptBuilder("ae3b79fe-4d32-46a0-a756-f6bfc4c19c73")
                .datatype(notApplicable)
                .conceptClass(misc)
                .name("21a9e890-2026-447c-a70b-8cd8992feecb", "Firewood", Locale.ENGLISH, ConceptNameType.FULLY_SPECIFIED)
                .build());

        Concept charcoal = install(new ConceptBuilder("13635bd8-b5f2-470a-9ff1-80fd465f6f53")
                .datatype(notApplicable)
                .conceptClass(misc)
                .name("adeca7fa-ba9f-4229-a7b7-2ffe64e10c92", "charcoal for cooking", Locale.ENGLISH, ConceptNameType.FULLY_SPECIFIED)
                .build());

        Concept paraffin = install(new ConceptBuilder("673079b3-1af7-4213-a6ed-02c20905cbf2")
                .datatype(notApplicable)
                .conceptClass(misc)
                .name("4bfa9e20-106e-4508-ae37-997e8919a4e3", "Paraffin/Kerosone", Locale.ENGLISH, ConceptNameType.FULLY_SPECIFIED)
                .build());

        //
        Concept bottledGas = install(new ConceptBuilder("235ff344-df36-4ae9-bbf6-a8d7828ec5ff")
                .datatype(notApplicable)
                .conceptClass(misc)
                .name("2987b242-165f-4ee1-8f00-9bb18ccb0805", "Bottled Gas", Locale.ENGLISH, ConceptNameType.FULLY_SPECIFIED)
                .build());


        Concept doNotKnow = install(new ConceptBuilder("1fa4b4a6-b849-4442-94d0-f46f4460ce38")
                .datatype(notApplicable)
                .conceptClass(misc)
                .name("1fa4b4a6-b849-4442-94d0-f46f4460ce38", "Don't Know", Locale.ENGLISH, ConceptNameType.FULLY_SPECIFIED)
                .build());

//        Concept enegrySourceForLighting = install(new ConceptBuilder("48c7ed3b-3775-4c95-8dd1-0b12e2453007")
//                .datatype(notApplicable)
//                .conceptClass(misc)
//                .name("edca6430-1e0b-40f7-868e-0b1d68eb7c1f", "Main energy source for lighting", Locale.ENGLISH, ConceptNameType.FULLY_SPECIFIED)
//                .answers(electricity,gas, solar,paraffinLamp, torch, other)
//                .build());

        Concept enegryForLighting = install(new ConceptBuilder("79f2c8bd-68ee-4dde-9af4-3f594052df1b")
                .datatype(coded)
                .conceptClass(misc)
                .name("9243dd06-bb9a-4b7d-8bbc-e82d16c30e65", "Main energy source for household lighting", Locale.ENGLISH, ConceptNameType.FULLY_SPECIFIED)
                .answers(electricity,gas, solar,paraffinLamp, torch, other)
                .build());

        Concept waterForDrinking = install(new ConceptBuilder("9243dd06-bb9a-4b7d-8bbc-e82d16c30e65")
                .datatype(coded)
                .conceptClass(misc)
                .name("4357b198-b99b-4be1-862d-238f29a993a3", "Main source of drinking water for household", Locale.ENGLISH, ConceptNameType.FULLY_SPECIFIED)
                .answers(pipedWater, fromWell, spring, borehole, bottledWater, other)
                .build());

        Concept energyForCooking = install(new ConceptBuilder("5755fdfc-586b-4816-9223-f45a1422e20a")
                .datatype(coded)
                .conceptClass(misc)
                .name("e3b93fa3-2a28-4884-b78f-d7f55d64bb47", "Fuel for household cooking", Locale.ENGLISH, ConceptNameType.FULLY_SPECIFIED)
                .answers(electricity, firewood, charcoal,bottledGas,paraffin,other)
                .build());

        Concept earth = install(new ConceptBuilder("47baac9f-9d34-4d5f-961a-cdfaac2c590c")
                .datatype(notApplicable)
                .conceptClass(misc)
                .name("26c83fbd-e2fc-41a7-bf01-ab18aed9b74e", "Earth/Sand", Locale.ENGLISH, ConceptNameType.FULLY_SPECIFIED)
                .build());

        Concept cement = install(new ConceptBuilder("254e6d65-e086-40c8-94fe-887200292c60")
                .datatype(notApplicable)
                .conceptClass(misc)
                .name("17f81230-bdc8-42cd-bf6f-c4d0d0c6a945", "Building cement", Locale.ENGLISH, ConceptNameType.FULLY_SPECIFIED)
                .build());

        Concept tiles = install(new ConceptBuilder("2ea253ad-2a61-4d2d-8896-42fda71fd170")
                .datatype(notApplicable)
                .conceptClass(misc)
                .name("d0303e15-eee9-46dd-9604-d550ec7921b2", "Tiles", Locale.ENGLISH, ConceptNameType.FULLY_SPECIFIED)
                .build());

        Concept typeOfHouseFloor = install(new ConceptBuilder("07f1ce88-dd02-47ff-8f0c-b39fc8616345")
                .datatype(coded)
                .conceptClass(misc)
                .name("babc3d83-a2c5-4433-8007-09c9aea6460c", "Type of house floor", Locale.ENGLISH, ConceptNameType.FULLY_SPECIFIED)
                .answers(earth, cement, tiles, other)
                .build());

        Concept mud = install(new ConceptBuilder("64f04067-ba95-42d1-84c0-d1b9fee1d03c")
                .datatype(notApplicable)
                .conceptClass(misc)
                .name("1774c921-3157-4f48-9a07-ff622f64b1c0", "Mud for building", Locale.ENGLISH, ConceptNameType.FULLY_SPECIFIED)
                .build());
//
        Concept typeOfHouseWalls = install(new ConceptBuilder("9c7bd3d9-096f-4f33-9781-2b3a23fee8ed")
                .datatype(coded)
                .conceptClass(misc)
                .name("afe52ff4-6bd3-4e03-b06e-9569d39beea2", "Type of house walls", Locale.ENGLISH, ConceptNameType.FULLY_SPECIFIED)
                .answers(cement, mud, other)
                .build());

        Concept grass = install(new ConceptBuilder("5eec9066-13b0-4f9f-a236-0fbaa8d7a836")
                .datatype(notApplicable)
                .conceptClass(misc)
                .name("19faabb5-4904-41d8-b8ac-2f05786453eb", "Grass", Locale.ENGLISH, ConceptNameType.FULLY_SPECIFIED)
                .build());

        Concept typeOfHouseRoof = install(new ConceptBuilder("7ee0bf27-9473-48d0-b9e5-cdfe67d34ab2")
                .datatype(coded)
                .conceptClass(misc)
                .name("60e83213-a119-4092-aa57-802a2d47f36f", "Type of house roof", Locale.ENGLISH, ConceptNameType.FULLY_SPECIFIED)
                .answers(tiles,grass, other)
                .build());
    }
}
