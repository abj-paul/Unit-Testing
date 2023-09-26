mod math;
use math::MathUtil::FunctionCollection;
fn main() {
    // You can call the encapsulated functions like this:
    let result1 = FunctionCollection::sum_up_to_n(5);
    let result2 = FunctionCollection::is_even(10);

    println!("Result 1: {}", result1);
    println!("Result 2: {}", result2);
}
