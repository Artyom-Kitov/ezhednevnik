import React from "react"
import * as ReactDOMClient from 'react-dom/client'
import App from './App'
import './css/main.css'

const app = ReactDOMClient.createRoot(document.getElementById("app"))

// const cors = require("cors")

// app.use(cors())

// app.listen(8080, () => {
//   console.log('Сервер запущен на порту 8080');
// });

app.render(<App/>)