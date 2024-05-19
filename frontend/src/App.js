import React from "react"
import Header from "./components/Header"
import Tasks from "./components/Tasks"
import AddTask from "./components/AddTask"
import axios from "axios"

const baseUrl = "http://localhost:8080/tasks"

class App extends React.Component {

  constructor(props) {
    super(props)

    this.state = {
      tasks: []
    }

    this.addTask = this.addTask.bind(this)
    this.editTask = this.editTask.bind(this)
    this.deleteTask = this.deleteTask.bind(this)
  }

  componentDidMount() {
    this.fetchTasks();
  }

  fetchTasks() {
    axios.get(baseUrl)
      .then((res) => {
        this.setState({ tasks: res.data });
      })
      .catch((error) => {
        console.error("Ошибка при получении данных из бэкенда:", error);
      });
  }

  addTask(task) {
    console.log(task)
    axios.post(baseUrl, task)
      .then((res) => {
        this.fetchTasks();
      })
      .catch((error) => {
        console.error("Ошибка при добавлении задачи:", error);
      });
  }

  editTask(task) {
    axios.put(`${baseUrl}/${task.id}`, task)
      .then((res) => {
        this.fetchTasks();
      })
      .catch((error) => {
        console.error("Ошибка при редактировании задачи:", error);
      });
  }

  deleteTask(id) {
    axios.delete(`${baseUrl}/${id}`)
      .then((res) => {
        this.fetchTasks();
      })
      .catch((error) => {
        console.error("Ошибка при удалении задачи:", error);
      });
  }

  render() {
    return (
      <div>
        <Header title="TaskGuru" />
        <main>
          <Tasks tasks={this.state.tasks} onEdit={this.editTask} onDelete={this.deleteTask} />
        </main>
        <aside>
          <AddTask onAdd={this.addTask} />
        </aside>
      </div>
    );
  }
}

export default App
