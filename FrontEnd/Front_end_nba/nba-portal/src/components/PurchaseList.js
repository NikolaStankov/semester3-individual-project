import SinglePurchase from "./SinglePurchase";

const PurchaseList = (props) => {
  return (
    <div className="purchase-list">
      {props.purchases.map((purchase) => (
        <SinglePurchase purchase={purchase} />
      ))}
    </div>
  );
};

export default PurchaseList;
