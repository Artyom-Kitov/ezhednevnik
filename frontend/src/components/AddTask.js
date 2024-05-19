import React from "react"

class AddTask extends React.Component {
  taskAdd = {};

  constructor(props) {
    super(props);
    this.state = {
      name: "",
      description: "",
      priority: "",
      status: "",
      deadline: null,
      isFormValid: false
    };
  }

  updateFormValidity() {
    const { name, priority, status, deadline } = this.state;
    const isValid = name !== "" && priority !== "" && status !== "" && deadline !== null;
    this.setState({ isFormValid: isValid });
  }

  render() {
    const { isFormValid } = this.state;
    return (
      <form
        ref={(el) => (this.myTask = el)}
        className="form"
      >
        <input
          placeholder="Task Name"
          value={this.state.name}
          onChange={(e) => {
            this.setState({ name: e.target.value }, () => this.updateFormValidity());
          }}
        />
        <textarea
          placeholder="Description"
          value={this.state.description}
          onChange={(e) => {
            this.setState({ description: e.target.value }, () => this.updateFormValidity());
          }}
        />
        <select
          value={this.state.priority}
          onChange={(e) => {
            this.setState({ priority: e.target.value }, () => this.updateFormValidity());
          }}
        >
          <option value="">Select Priority</option>
          <option value="low">Low</option>
          <option value="medium">Medium</option>
          <option value="high">High</option>
          <option value="critical">Critical</option>
        </select>
        <select
          value={this.state.status}
          onChange={(e) => {
            this.setState({ status: e.target.value }, () => this.updateFormValidity());
          }}
        >
          <option value="">Select Status</option>
          <option value="to do">To Do</option>
          <option value="in progress">In Progress</option>
          <option value="done">Done</option>
        </select>

        <h3>Deadline</h3> 

        <input
          type="date"
          value={this.state.deadline}
          onChange={(e) => {
            console.log(e.target.value)
            this.setState({ deadline: e.target.value }, () => this.updateFormValidity());
          }}
        />


        <button
          type="button"
          style={{ backgroundColor: isFormValid ? "#00b126" : "#140" }}
          disabled={!isFormValid}
          onClick={() => {
            this.taskAdd = {
              name: this.state.name,
              description: this.state.description,
              priority: this.state.priority,
              status: this.state.status,
              deadline: this.state.deadline
            };
            if (this.props.task) this.taskAdd.id = this.props.task.id;
            this.props.onAdd(this.taskAdd);
            this.myTask.reset();
            this.setState({
              name: "",
              description: "",
              priority: "",
              status: "",
              deadline: null,
              isFormValid: false
            }); 
          }}
        >
          Add
        </button>
      </form>
    );
  }
}

export default AddTask;
