<template>
  <div>
    <h1>Appointment List</h1>
    <button @click="navigateToCreateAppointment" class="fancy-button create-button">Create Appointment</button>
    <div class="appointment-table">
      <div class="appointment-row header">
        <div class="cell">Patient</div>
        <div class="cell">Doctor</div>
        <div class="cell">Time</div>
        <div class="cell">Date</div>
        <div class="cell">Actions</div>
      </div>
      <div v-for="appointment in appointments" :key="appointment.id" class="appointment-row">
        <!-- Display patient and doctor names based on the fetched data -->
        <div class="cell">
          {{ appointment.patientName || "Unknown Patient" }}
        </div>
        <div class="cell">
          {{ appointment.doctorName || "Unknown Doctor" }}
        </div>
        <div class="cell">
          {{ formatTime(appointment.appointmentTime) }}
        </div>
        <div class="cell">
          {{ formatDate(appointment.appointmentDate) }}
        </div>
        <div class="cell">
          <button @click="navigateToUpdateAppointment(appointment.id)" class="fancy-button update-button">Update</button>
          <button @click="deleteAppointment(appointment.id)" class="fancy-button delete-button">Delete</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      appointments: [] // Stores the processed appointment data
    };
  },
  created() {
    this.fetchAppointments();
  },
  methods: {
    async fetchAppointments() {
      try {
        const response = await axios.get("http://localhost:8080/appointment");
        const appointments = response.data;

        // Map patient and doctor details for each appointment
        const populatedAppointments = await Promise.all(
          appointments.map(async (appointment) => {
            const patientName = await this.fetchPatientName(appointment.patientId);
            const doctorName = await this.fetchDoctorName(appointment.doctorId);

            return {
              ...appointment,
              patientName,
              doctorName
            };
          })
        );

        this.appointments = populatedAppointments;
      } catch (error) {
        console.error("Error fetching appointments:", error);
      }
    },

    async fetchPatientName(patientId) {
      try {
        const response = await axios.get(`http://localhost:8080/patient/${patientId}`);
        const patient = response.data;
        return `${this.capitalizeName(patient.fname)} ${this.capitalizeName(patient.lname)}`;
      } catch (error) {
        console.error(`Error fetching patient with ID ${patientId}:`, error);
        return "Unknown Patient";
      }
    },

    async fetchDoctorName(doctorId) {
      try {
        const response = await axios.get(`http://localhost:8080/doctor/${doctorId}`);
        const doctor = response.data;
        return `${doctor.title} ${this.capitalizeName(doctor.fname)} ${this.capitalizeName(doctor.lname)}`;
      } catch (error) {
        console.error(`Error fetching doctor with ID ${doctorId}:`, error);
        return "Unknown Doctor";
      }
    },

    deleteAppointment(id) {
      axios
        .delete(`http://localhost:8080/appointment/${id}`)
        .then(() => {
          this.appointments = this.appointments.filter((appointment) => appointment.id !== id);
        })
        .catch((error) => {
          console.error("Error deleting appointment:", error);
        });
    },

    navigateToCreateAppointment() {
      this.$router.push({ name: "CreateAppointment" });
    },

    navigateToUpdateAppointment(id) {
      this.$router.push({ name: "AppointmentUpdate", params: { id } });
    },

    formatDate(date) {
      return date || "Invalid Date";
    },

    capitalizeName(name) {
      return name
        .split(" ")
        .map((word) => word.charAt(0).toUpperCase() + word.slice(1))
        .join(" ");
    },

    formatTime(time) {
      return time || "Invalid Time";
    }
  }
};
</script>

<style scoped>
.appointment-table {
  display: flex;
  flex-direction: column;
  margin-top: 20px;
}

.appointment-row {
  display: flex;
  border-bottom: 1px solid black; /* Set border color to black */
}

.appointment-row.header {
  font-weight: bold;
  background-color: #f0f0f0;
}

.cell {
  padding: 10px;
  flex: 1;
}

.cell:nth-child(5) {
  flex: 0.5;
}

.button-container {
  display: flex;
  justify-content: space-between;
  width: 100px;
}

.fancy-button {
  margin-right: 10px;
  border: none;
  color: white;
  padding: 10px 20px;
  font-size: 16px;
  border-radius: 12px;
  cursor: pointer;
  transition: background-color 0.3s ease, transform 0.3s ease;
}

.fancy-button:hover {
  opacity: 0.8;
  transform: scale(1.05);
}

.create-button {
  background-color: #4CAF50; /* Green */
}

.create-button:hover {
  background-color: #45a049;
}

.update-button {
  background-color: #FFA500; /* Yellow */
}

.update-button:hover {
  background-color: #e59400;
}

.delete-button {
  background-color: #f44336; /* Red */
}

.delete-button:hover {
  background-color: #da190b;
}
</style>
