import React from "react"
import AddTask from "./AddTask";
import {IoCloseCircleSharp} from 'react-icons/io5'
import { MdEdit } from "react-icons/md";

class Task extends React.Component {

  constructor(props) {
    super(props)
    this.state = {
      editTask: false
    }
  }

  task = this.props.task

  render() {
    return (
      <div className="task" >
        <IoCloseCircleSharp onClick={() => this.props.onDelete(this.task.id)} className="delete-icon"/> 
        <MdEdit onClick={() => {
          this.setState({
            editTask: !this.state.editTask
          })
        }} className="edit-icon"/> 
        <h3>Task {this.task.id}) {this.task.name}: {this.task.status}</h3>
        <p>{this.task.description}</p>


        { 
          this.task.priority === "critical" ? (
            <b>Особо важно: {this.task.priority}</b>
          ) : (
            <b>Не особо важно: {this.task.priority}</b>
          )
        }

        {this.state.editTask && <AddTask task={this.task} onAdd={this.props.onEdit} />}

      </div> 
    )
  }

}

export default Task