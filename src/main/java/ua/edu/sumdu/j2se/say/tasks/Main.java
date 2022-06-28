package ua.edu.sumdu.j2se.say.tasks;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import static ua.edu.sumdu.j2se.say.tasks.ListTypes.types.ARRAY;
import static ua.edu.sumdu.j2se.say.tasks.ListTypes.types.LINKED;
import static ua.edu.sumdu.j2se.say.tasks.TaskListFactory.createTaskList;

public class Main {
	public static void main(String[] args) throws CloneNotSupportedException, IOException {
		System.out.println("Hello");
		Task a = new Task("A", LocalDateTime.now());
		Task b = new Task("B", LocalDateTime.now().plusSeconds(1));
		Task c = new Task("C", LocalDateTime.now().plusSeconds(10));
		Task d = new Task("D", LocalDateTime.now().plusSeconds(3600));
		Task e = new Task("E", LocalDateTime.now().plusSeconds(-1));
		Task f = new Task("F", LocalDateTime.now().plusSeconds(3));
		Task g = new Task("G", LocalDateTime.now().plusSeconds(5));
		Task h = new Task("H", LocalDateTime.now().plusSeconds(3*3600));
		Task i = new Task("I", LocalDateTime.now().plusSeconds(24*3*3600));
		Task j = new Task("J", LocalDateTime.now().plusSeconds(24*7*3600));
		Task k = new Task("K",LocalDateTime.now().plusSeconds(-1000));
		Task l = new Task("L", LocalDateTime.now(), LocalDateTime.now().plusSeconds(1),1);
		Task m = new Task("M", LocalDateTime.now().plusSeconds(1), LocalDateTime.now().plusSeconds(10),1);
		Task n = new Task("N", LocalDateTime.now().plusSeconds(10), LocalDateTime.now().plusSeconds(1000),10);
		Task o = new Task("O", LocalDateTime.now().plusSeconds(-1), LocalDateTime.now().plusSeconds(100),2);
		Task p = new Task("P", LocalDateTime.now().plusSeconds(1000), LocalDateTime.now().plusSeconds(3*3600),60);
		Task q = new Task("Q", LocalDateTime.now().plusSeconds(-1000), LocalDateTime.now().plusSeconds(1000),10);
		Task r = new Task("R", LocalDateTime.now(), LocalDateTime.now().plusSeconds(10),1);
		Task s = new Task("S", LocalDateTime.now().plusSeconds(1), LocalDateTime.now().plusSeconds(1000),10);
		Task t = new Task("T", LocalDateTime.now().plusSeconds(1), LocalDateTime.now().plusSeconds(2),1);

		a.setActive(true);
		b.setActive(true);
		c.setActive(true);
		d.setActive(true);
		e.setActive(true);
		f.setActive(true);
		g.setActive(true);
		h.setActive(true);
		i.setActive(true);
		j.setActive(true);
		k.setActive(true);
		l.setActive(true);
		m.setActive(true);
		n.setActive(true);
		o.setActive(true);
		p.setActive(true);
		q.setActive(true);
		r.setActive(true);
		s.setActive(true);
		t.setActive(true);

		System.out.println("a: " + a);
		System.out.println("a.nextTimeAfter(now): " + a.nextTimeAfter(LocalDateTime.now()));
		System.out.println("s.nextTimeAfter(now+10): " + s.nextTimeAfter(LocalDateTime.now().plusSeconds(10)));

		AbstractTaskList al = createTaskList(ARRAY);
		AbstractTaskList ll = createTaskList(LINKED);

		al.add(a);
		al.add(b);
		al.add(c);
		al.add(d);
		al.add(e);
		al.add(f);
		al.add(g);
		al.add(h);
		al.add(i);
		al.add(j);
		al.add(k);
		al.add(l);
		al.add(m);
		al.add(n);
		al.add(o);
		al.add(p);
		al.add(q);
		al.add(r);
		al.add(s);
		al.add(t);



		al.remove(t);
		al.remove(s);
		al.remove(r);
		al.remove(q);
		al.remove(p);
		al.remove(o);
		al.remove(n);
		al.remove(m);
		al.remove(l);
		al.remove(k);
		al.remove(j);
		al.remove(i);

		System.out.println("al: " + al);
		System.out.println("al.incoming(10, 100)" + Tasks.incoming(al, LocalDateTime.now(), LocalDateTime.now().plusSeconds(1)));
		System.out.println("al size: " + al.size());
		System.out.println("al length: " + al.thisArraySize());

		ll.add(a);
		ll.add(b);
		ll.add(c);
		ll.add(d);
		ll.add(e);
		ll.add(f);
		ll.add(g);
		ll.add(h);
		ll.add(i);
		ll.add(j);
		ll.add(k);
		ll.add(l);
		ll.add(m);
		ll.add(n);
		ll.add(o);
		ll.add(p);
		ll.add(q);
		ll.add(r);
		ll.add(s);
		ll.add(t);


		System.out.println("ll: " + ll);
		System.out.println("ll.incoming(10, 100)" + Tasks.incoming(ll, LocalDateTime.now().plusSeconds(1), LocalDateTime.now().plusSeconds(10)));
		System.out.println("ll.size: " + ll.size());


		for (Task abc : al
			 ) {
			System.out.println("abc: " + abc.toString());
		}

		AbstractTaskList al1 = al.clone();
		System.out.println("al1: " + al1.toString());

		AbstractTaskList ll1 = ll.clone();
		System.out.println("ll1: " + ll1.toString());


		Task zzz = a.clone();
		System.out.println("zzz: " + zzz.toString());

		System.out.println("a.hashcode: " + a.hashCode());
		System.out.println("al.hashcode: " + al.hashCode());



		System.out.println(al.getClass());
		System.out.println(ll.getClass());



		Object obj = new ArrayTaskList();

		ArrayTaskList atl1 = new ArrayTaskList();

	System.out.println(al.equals(atl1));
	System.out.println(al1.equals(ll));
	System.out.println(atl1.getClass());
	System.out.println(obj.getClass());
	System.out.println(LocalDateTime.now());

	System.out.println(Tasks.calendar(al, LocalDateTime.now(), LocalDateTime.now().plusSeconds(1000000000)));
	System.out.println(Tasks.calendar(ll, LocalDateTime.now(), LocalDateTime.now().plusSeconds(1000000000)));

		File file = new File("writeBinaryFile");
		TaskIO.writeText(ll, file);
		AbstractTaskList readFromFile = new ArrayTaskList();
		TaskIO.readText(readFromFile, file);
		System.out.println(readFromFile);
		System.out.println(readFromFile.getTask(0).toString());
		System.out.println(readFromFile.getTask(10).toString());


	}
}
