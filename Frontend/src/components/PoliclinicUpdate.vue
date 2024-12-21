<template>
  <div>
    <h1>Update Policlinic</h1>
    <form @submit.prevent="submitUpdate">
      <div>
        <label for="name">Name:</label>
        <input type="text" v-model="policlinic.name" id="name" required />
      </div>
      <div>
        <label for="floor">Floor:</label>
        <input type="number" v-model="policlinic.floor" id="floor" required />
      </div>
      <button type="submit" class="fancy-button add-button">Update</button>
    </form>
  </div>
</template>

<script>
import axios from "axios";

export default {
  props: ["id"], // Prop to receive the Policlinic ID
  data() {
    return {
      policlinic: {
        name: "",
        floor: null
      }
    };
  },
  created() {
    this.fetchPoliclinic();
  },
  methods: {
    fetchPoliclinic() {
      // Use either prop or route parameter for ID
      const id = this.id || this.$route.params.id;
      if (!id) {
        console.error("No policlinic ID provided");
        return;
      }

      axios
        .get(`http://localhost:8080/policlinic/${id}`)
        .then((response) => {
          this.policlinic = response.data;
          console.log("Fetched policlinic:", this.policlinic);
        })
        .catch((error) => {
          console.error("Error fetching policlinic:", error);
        });
    },
    submitUpdate() {
      // Use either prop or route parameter for ID
      const id = this.id || this.$route.params.id;
      if (!id) {
        console.error("No policlinic ID provided");
        return;
      }

      axios
        .put(`http://localhost:8080/policlinic/${id}`, this.policlinic)
        .then(() => {
          console.log("Policlinic updated successfully");
          this.$router.push("/PoliclinicList");
        })
        .catch((error) => {
          console.error("Error updating policlinic:", error);
        });
    }
  }
};
</script>

<style scoped>
/* Your styling */
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
.fancy-button {
  background-color: #4caf50;
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
  background-color: #008cba;
}
.add-button:hover {
  background-color: #007bb5;
}
</style>
