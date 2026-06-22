import axios from 'axios';

const myApi = axios.create({
    baseURL: 'http://localhost:8080/api',
    withCredentials: true
});

export default myApi;