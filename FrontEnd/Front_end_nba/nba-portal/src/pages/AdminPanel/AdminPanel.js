import UploadTicketForm from "../../components/UploadTicketForm";

const AdminPanel = (props) => {
  return (
    <>
      {props.loggedUser && props.loggedUser.role.some((r) => r === "ADMIN") && (
        <UploadTicketForm />
      )}
    </>
  );
};

export default AdminPanel;
