<template>
  <div>
    <h1>Create Appointment</h1>
    <form @submit.prevent="createAppointment">
      <div>
        <label for="patient">Patient:</label>
        <select v-model="appointment.patientId" required>
          <option v-for="patient in patients" :key="patient.id" :value="patient.id">
            {{ capitalizeName(`${patient.fname} ${patient.lname}`) }}
          </option>
        </select>
      </div>
      <div>
        <label for="doctor">Doctor:</label>
        <select v-model="appointment.doctorId" required>
          <option v-for="doctor in doctors" :key="doctor.id" :value="doctor.id">
            {{ capitalizeName(`${doctor.title} ${doctor.fname} ${doctor.lname}`) }}
          </option>
        </select>
      </div>
      <div>
        <label for="time">Time:</label>
        <select v-model="appointment.timeSlot" required>
          <option v-for="(timeLabel, timeKey) in timeSlots" :key="timeKey" :value="timeKey">
            {{ timeLabel }}
          </option>
        </select>
      </div>
      <div>
        <label for="date">Date:</label>
        <input type="date" v-model="appointment.date" required />
      </div>
    </form>
    <button type="submit" class="fancy-button add-button">Create Appointment</button>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      appointment: {
        patientId: "",
        doctorId: "",
        timeSlot: "",
        date: ""
      },
      patients: [],
      doctors: [],
      timeSlots: {
        S0830: "08:30 AM",
        S0900: "09:00 AM",
        S0930: "09:30 AM",
        S1000: "10:00 AM",
        S1030: "10:30 AM",
        S1100: "11:00 AM",
        S1130: "11:30 AM",
        S1200: "12:00 PM",
        S1230: "12:30 PM",
        S1300: "01:00 PM",
        S1330: "01:30 PM",
        S1400: "02:00 PM",
        S1430: "02:30 PM",
        S1500: "03:00 PM",
        S1530: "03:30 PM",
        S1600: "04:00 PM"
      }
    };
  },
  created() {
    this.fetchPatients();
    this.fetchDoctors();
  },
  methods: {
    fetchPatients() {
      axios.get("http://localhost:8080/patient")
        .then((response) => {
          this.patients = response.data;
        })
        .catch((error) => {
          console.error("Error fetching patients:", error);
        });
    },
    fetchDoctors() {
      axios.get("http://localhost:8080/doctor")
        .then((response) => {
          this.doctors = response.data;
        })
        .catch((error) => {
          console.error("Error fetching doctors:", error);
        });
    },
    createAppointment() {
      const timeMapping = {
        S0830: "08:30:00",
        S0900: "09:00:00",
        S0930: "09:30:00",
        S1000: "10:00:00",
        S1030: "10:30:00",
        S1100: "11:00:00",
        S1130: "11:30:00",
        S1200: "12:00:00",
        S1230: "12:30:00",
        S1300: "13:00:00",
        S1330: "13:30:00",
        S1400: "14:00:00",
        S1430: "14:30:00",
        S1500: "15:00:00",
        S1530: "15:30:00",
        S1600: "16:00:00"
      };

      const newAppointment = {
        patientId: this.appointment.patientId,
        doctorId: this.appointment.doctorId,
        appointmentTime: timeMapping[this.appointment.timeSlot],
        appointmentDate: this.appointment.date
      };

      axios.post("http://localhost:8080/appointment", newAppointment)
        .then(() => {
          this.$router.push({ name: "AppointmentList" });
        })
        .catch((error) => {
          console.error("Error creating appointment:", error);
        });
    },
    capitalizeName(name) {
      return name.split(" ").map(word => word.charAt(0).toUpperCase() + word.slice(1)).join(" ");
    }
  }
};
</script>

  <style scoped>
  form {
    display: flex;
    flex-direction: column;
  }
  
  div {
    margin-bottom: 10px;
  }
  
  label {
    margin-right: 10px;
  }
  
  input,
  select {
    padding: 5px;
    margin-bottom: 10px;
  }
  
  .fancy-button {
    padding: 10px;
    background-color: #007bff;
    color: white;
    border: none;
    cursor: pointer;
  }
  
  .fancy-button:hover {
    background-color: #0056b3;
  }
  
  .add-button {
    align-self: flex-start;
  }
  </style>
  