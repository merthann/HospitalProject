<template>
  <div>
    <h1>Update Appointment</h1>
    <form @submit.prevent="updateAppointment">
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
        <select v-model="appointment.time" required>
          <option v-for="time in Object.keys(timeSlots)" :key="time" :value="time">
            {{ formatTimeSlot(time) }}
          </option>
        </select>
      </div>
      <div>
        <label for="date">Date:</label>
        <input type="date" v-model="appointment.date" required />
      </div>
      <button type="submit" class="fancy-button update-button">Update</button>
    </form>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      appointment: {
        id: "",
        patientId: "",
        doctorId: "",
        time: "",
        date: "",
      },
      patients: [],
      doctors: [],
      timeSlots: {
        S0830: 1,
        S0900: 2,
        S0930: 3,
        S1000: 4,
        S1030: 5,
        S1100: 6,
        S1130: 7,
        S1200: 8,
        S1230: 0,
        S1300: 10,
        S1330: 11,
        S1400: 12,
        S1430: 13,
        S1500: 14,
        S1530: 15,
        S1600: 16,
      },
    };
  },
  created() {
    this.fetchPatients();
    this.fetchDoctors();
    this.fetchAppointmentDetails();
  },
  methods: {
    fetchPatients() {
      axios
        .get("http://localhost:8080/patient")
        .then((response) => {
          this.patients = response.data;
        })
        .catch((error) => {
          console.error("Error fetching patients:", error);
        });
    },
    fetchDoctors() {
      axios
        .get("http://localhost:8080/doctor")
        .then((response) => {
          this.doctors = response.data;
        })
        .catch((error) => {
          console.error("Error fetching doctors:", error);
        });
    },
    fetchAppointmentDetails() {
      const id = this.$route.params.id;
      axios
        .get(`http://localhost:8080/appointment/${id}`)
        .then((response) => {
          const appointment = response.data;

          // Assuming your backend response for `getAppointmentById` contains patientId, doctorId, time, and date.
          this.appointment.id = appointment.id;
          this.appointment.patientId = appointment.patientId;
          this.appointment.doctorId = appointment.doctorId;
          this.appointment.time = appointment.time;
          this.appointment.date = appointment.date; // Assuming the backend sends the date in yyyy-mm-dd format.
        })
        .catch((error) => {
          console.error("Error fetching appointment details:", error);
        });
    },
    updateAppointment() {
      const updatedAppointment = {
        patientId: this.appointment.patientId,
        doctorId: this.appointment.doctorId,
        time: this.appointment.time,
        date: this.appointment.date,
      };

      axios
        .put(`http://localhost:8080/appointment/${this.appointment.id}`, updatedAppointment)
        .then((response) => {
          console.log("Update successful:", response.data);
          this.$router.push({ name: "AppointmentList" });
        })
        .catch((error) => {
          console.error("Error updating appointment:", error);
        });
    },
    formatTimeSlot(timeString) {
      const hour = timeString.slice(1, 3);
      const minute = timeString.slice(3);
      return `${hour}:${minute}`;
    },
    capitalizeName(name) {
      return name
        .split(" ")
        .map((word) => word.charAt(0).toUpperCase() + word.slice(1))
        .join(" ");
    },
  },
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

.update-button {
  align-self: flex-start;
}
</style>
