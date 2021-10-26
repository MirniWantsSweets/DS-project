
    active = true;
    Start_time.add(clock.localtime());

  }
  public void stop(SingletonClock clock){ //Inidica finalizacion de intervalo de trabajo

    active = false;
    End_time.add(clock.localtime());
  }
  public void setDone(){ // Indica la finalizacion de la tarea
    done= true;
  }



  private void changename(String newname){ //función para poder cambiar el nombre en cualquier momento.
    name = newname;
  }

  @Override
  public void update(){ // El observado notifica al observable, y se llama a esta función
    if(this.active==true){
      ticks++;
    }

  }

  void displayTask() //Muestra nombre, tiempo total de la tarea y fechas de inicio y finalizacion de los intervalos
  {
    System.out.println("Task: " + name + " total time = " + calculateTotalTime() + " TIME INTERVALS: ");

    if(started) System.out.println("Task Started: " + Start_time.get(0).getMonth() + "/" + Start_time.get(0).getDayOfMonth() + "/" + Start_time.get(0).getYear());

    for(int i=0; i < End_time.size(); i++)
    {
      System.out.println("Interval " + i + ": ");
      System.out.println("From: " + Start_time.get(i).getMonth() + "/" + Start_time.get(i).getDayOfMonth() + "/" + Start_time.get(i).getYear());
      System.out.println("To: " + End_time.get(i).getMonth() + "/" + End_time.get(i).getDayOfMonth() + "/" + End_time.get(i).getYear());
    }

  }
}

