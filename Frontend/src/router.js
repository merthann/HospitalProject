import { createRouter, createWebHistory } from 'vue-router';
import DoctorList from './components/DoctorList.vue';
import DoctorUpdate from './components/DoctorUpdate.vue';
import PatientList from './components/PatientList.vue';
import PatientUpdate from './components/PatientUpdate.vue';
import PoliclinicList from './components/PoliclinicList.vue';
import PoliclinicUpdate from './components/PoliclinicUpdate.vue';
import MedicineList from './components/MedicineList.vue';
import MedicineUpdate from './components/MedicineUpdate.vue';
import CreateMedicine from './components/CreateMedicine.vue';
import AssistantList from './components/AssistantList.vue';
import AssistantUpdate from './components/AssistantUpdate.vue';
import CreateDoctor from './components/CreateDoctor.vue';
import CreatePoliclinic from './components/CreatePoliclinic.vue';
import CreateAssistant from './components/CreateAssistant.vue';
import AppointmentList from './components/AppointmentList.vue';
import AppointmentUpdate from './components/AppointmentUpdate.vue';
import CreateAppointment from './components/CreateAppointment.vue';
import CreatePatient from './components/CreatePatient.vue';

const routes = [
  {
    path: '/AppointmentList',
    name: 'AppointmentList',
    component: AppointmentList,
  },
  {
    path: '/Appointment/update/:id',
    name: 'AppointmentUpdate',
    component: AppointmentUpdate,
    props: true,
  },
  {
    path: '/CreatePatient', 
    name: 'CreatePatient',
    component: CreatePatient,
  },
  {
    path: '/CreateAppointment',
    name: 'CreateAppointment',
    component: CreateAppointment,
  },
  {
    path: '/DoctorList',
    name: 'DoctorList',
    component: DoctorList,
  },
  {
    path: '/Doctor/update/:id',
    name: 'DoctorUpdate',
    component: DoctorUpdate,
    props: true,
  },
  {
    path: '/CreateDoctor',
    name: 'CreateDoctor',
    component: CreateDoctor,
  },
  {
    path: '/PoliclinicList',
    name: 'PoliclinicList',
    component: PoliclinicList,
  },
  {
    path: '/Policlinic/update/:id',
    name: 'PoliclinicUpdate',
    component: PoliclinicUpdate,
    props: true,
  },
  {
    path: '/CreatePoliclinic',
    name: 'CreatePoliclinic',
    component: CreatePoliclinic,
  },
  {
    path: '/PatientList',
    name: 'PatientList',
    component: PatientList,
  },
  {
    path: '/Patient/update/:id',
    name: 'PatientUpdate',
    component: PatientUpdate,
    props: true,
  },
  {
    path: '/AssistantList',
    name: 'AssistantList',
    component: AssistantList,
  },
  {
    path: '/Assistant/update/:id',
    name: 'AssistantUpdate',
    component: AssistantUpdate,
    props: true,
  },
  {
    path: '/CreateAssistant',
    name: 'CreateAssistant',
    component: CreateAssistant,
  },
  {
    path: '/MedicineList',
    name: 'MedicineList',
    component: MedicineList,
  },
  {
    path: '/Medicine/update/:id',
    name: 'MedicineUpdate',
    component: MedicineUpdate,
    props: true,
  },
  {
    path: '/CreateMedicine',
    name: 'CreateMedicine',
    component: CreateMedicine,
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
