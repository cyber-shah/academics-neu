class Phone {
  String model;
  String manufacturer;
  int memory;
  boolean state;

  Phone(String model, String manufacturer, int memory) {
    this.model = model;
    this.manufacturer = manufacturer;
    this.memory = memory;
    this.state = false;
  }
  
  void turnOn() {
    this.state = true;
  }
  
  void turnOff() {
    this.state = false;
  }
}
