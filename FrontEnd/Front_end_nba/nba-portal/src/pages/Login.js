import LoginForm from "../components/LoginForm";

const Login = (props) => {
  return (
    <LoginForm
      updateLoggedUserProps={props.updateLoggedUserProps}
    />
  );
};

export default Login;
