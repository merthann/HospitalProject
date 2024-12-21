package com.example.hospital_management;

public class Queries {

	
	
	/*
	CREATE TABLE Patient (
		    id UUID PRIMARY KEY DEFAULT gen_random_uuid(), -- Automatically generates UUIDs
		    fname VARCHAR(100) NOT NULL,                   -- First name
		    lname VARCHAR(100) NOT NULL,                   -- Last name
		    identityno BIGINT UNIQUE NOT NULL,             -- Identity number
		    email VARCHAR(255) UNIQUE NOT NULL,            -- Email address
		    mobileno BIGINT NOT NULL,                      -- Mobile number
		    CONSTRAINT chk_identityno CHECK (identityno BETWEEN 10000000000 AND 99999999999),
		    CONSTRAINT chk_mobileno CHECK (mobileno BETWEEN 1000000000 AND 9999999999)
		);

	
	
	CREATE TABLE Policlinic (
    policlinicid SERIAL PRIMARY KEY,  -- Auto-generated ID
    name VARCHAR(255) NOT NULL,       -- Policlinic name
    floor INT NOT NULL                -- Floor number
);


CREATE TABLE Assistant (
    id SERIAL PRIMARY KEY,         -- Auto-generated ID
    fname VARCHAR(100) NOT NULL,   -- First name
    lname VARCHAR(100) NOT NULL,   -- Last name
    workdays VARCHAR(255)          -- Workdays (e.g., Mon-Fri)
);



CREATE TABLE Doctor (
    id SERIAL PRIMARY KEY,                  -- Auto-generated ID
    title VARCHAR(100),                     -- Title (e.g., Dr., Prof.)
    fname VARCHAR(100) NOT NULL,            -- First name
    lname VARCHAR(100) NOT NULL,            -- Last name
    policlinicid INT NOT NULL,              -- Foreign key to Policlinic
    assistantid SERIAL,                     -- Foreign key to Assistant
    CONSTRAINT fk_policlinic FOREIGN KEY (policlinicid) 
        REFERENCES Policlinic(policlinicid)
        ON DELETE CASCADE 
        ON UPDATE CASCADE,
    CONSTRAINT fk_assistant FOREIGN KEY (assistantid) 
        REFERENCES Assistant(id)
        ON DELETE CASCADE 
        ON UPDATE CASCADE
);


CREATE TABLE Category (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) UNIQUE NOT NULL
);



CREATE TABLE Appointment (
    id SERIAL PRIMARY KEY,                   -- Auto-generated ID
    patientid UUID NOT NULL,                 -- Foreign key to Patient
    doctorid INT NOT NULL,                   -- Foreign key to Doctor
    time TIME NOT NULL,          -- Proper time-based representation
    date DATE NOT NULL,          -- Appointment date
    CONSTRAINT fk_patient FOREIGN KEY (patientid) 
        REFERENCES Patient(id)
        ON DELETE CASCADE 
        ON UPDATE CASCADE,
    CONSTRAINT fk_doctor FOREIGN KEY (doctorid) 
        REFERENCES Doctor(id)
        ON DELETE CASCADE 
        ON UPDATE CASCADE
);


	
	CREATE TABLE Test (
    id SERIAL PRIMARY KEY,             -- Auto-generated ID
    name VARCHAR(255) NOT NULL,        -- Test name
    results TEXT                       -- Test results
);


CREATE TABLE Medicine (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,          -- Name of the medicine
    description TEXT,                       -- Description of the medicine
    category int NOT NULL        
);

ALTER TABLE Medicine
ADD CONSTRAINT fk_category
FOREIGN KEY (category)
REFERENCES Category(id)
ON DELETE CASCADE
ON UPDATE CASCADE;




	To look at medicines with the category names not the ids:
	
select medicine.name,medicine.description,category.name 
from medicine 
left join category on 
medicine.category = category.id
	
	
	
	*/
}
