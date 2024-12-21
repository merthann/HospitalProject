<template>
    <div>
      <h1>Create Patient</h1>
      <form @submit.prevent="createPatient">
        <div>
          <label for="fname">First Name:</label>
          <input type="text" v-model="patient.fname" id="fname" required />
        </div>
        <div>
          <label for="lname">Last Name:</label>
          <input type="text" v-model="patient.lname" id="lname" required />
        </div>
        <div>
  <label for="identityno">Identity Number:</label>
  <input
    type="text"
    v-model="patient.identityno"
    id="identityno"
    maxlength="11"
    @input="validateIdentityNo"
    required
  />
  <small class="info-text">Must be exactly 11 digits.</small>
</div>

<div>
  <label for="email">Email:</label>
  <input
    type="email"
    v-model="patient.email"
    id="email"
    @input="validateEmail"
    required
  />
  <small class="info-text">Must be a valid email address (e.g., user@example.com).</small>
</div>

<label for="mobileno">Mobile Number:</label>
<input
  type="text"
  v-model="patient.mobileno"
  id="mobileno"
  required
  maxlength = "10"
  pattern="^\d{10}$"
  />
  <small class="info-text">Must be exactly 11 digits.</small>

        <button type="submit" class="fancy-button add-button">Create Patient</button>
      </form>
    </div>
  </template>
  
  <script>
  import axios from "axios";
  
  export default {
    data() {
      return {
        patient: {
          fname: "",
          lname: "",
          identityno: "",
          email: "",
          mobileno: ""
        }
      };
    },
    methods: {
      createPatient() {
        axios
          .post("http://localhost:8080/patient", this.patient)
          .then(() => {
            console.log("Patient created successfully.");
            this.$router.push({ name: "PatientList" });
          })
          .catch((error) => {
            console.error("Error creating patient:", error);
          });
      }
    }
  };
  </script>
  
  <style scoped>
  /* Styling */
  body {
    background-color: lightblue;
  }
  h1 {
    color: white;
    text-align: center;
  }
  form {
    margin: 20px auto;
    width: 50%;
    text-align: center;
  }
  label {
    display: block;
    margin-bottom: 5px;
    font-weight: bold;
  }
  input {
    width: 100%;
    padding: 8px;
    margin-bottom: 10px;
    border: 1px solid #ccc;
    border-radius: 4px;
  }
  .info-text {
    font-size: 12px;
    color: #888;
    margin-bottom: 10px;
    display: block;
  }
  .fancy-button {
    background-color: #4CAF50;
    color: white;
    border: none;
    padding: 10px 20px;
    font-size: 16px;
    border-radius: 12px;
    cursor: pointer;
    transition: background-color 0.3s ease, transform 0.3s ease;
  }
  .fancy-button:hover {
    background-color: #45a049;
    transform: scale(1.05);
  }
  .add-button {
    background-color: #008CBA;
  }
  .add-button:hover {
    background-color: #007bb5;
  }
  </style>
  