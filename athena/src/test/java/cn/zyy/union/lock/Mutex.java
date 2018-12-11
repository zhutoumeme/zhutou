package cn.zyy.union.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Mutex implements Lock {

  private final Sync sync = new Sync();

  private static class Sync extends AbstractQueuedSynchronizer {

    @Override
    protected boolean tryAcquire(int arg) {
      if (compareAndSetState(0, 1)) {
        setExclusiveOwnerThread(Thread.currentThread());
        return true;
      }
      return false;
    }

    @Override
    protected boolean tryRelease(int arg) {
      if (Thread.currentThread() != getExclusiveOwnerThread()) {
        throw new IllegalMonitorStateException();
      }
      int c = getState() - arg;
      boolean free = false;
      if (c == 0) {
        setExclusiveOwnerThread(null);
        setState(0);
        free = true;
      } else {
        setState(c);
      }
      return free;
    }

    Condition newCondition() {
      return new ConditionObject();
    }

    @Override
    protected boolean isHeldExclusively() {
      return getState() == 0;
    }
  }

  @Override
  public void lock() {
    sync.acquire(1);
  }

  @Override
  public void lockInterruptibly() throws InterruptedException {
    sync.acquireInterruptibly(1);
  }

  @Override
  public boolean tryLock() {
    return sync.tryAcquire(1);
  }

  @Override
  public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
    return sync.tryAcquireNanos(1, unit.toNanos(time));
  }

  @Override
  public void unlock() {
    sync.release(1);
  }

  @Override
  public Condition newCondition() {
    return sync.newCondition();
  }

  public boolean isLocked() {
    return sync.isHeldExclusively();
  }

  public boolean hasQueuedThreads() {
    return sync.hasQueuedThreads();
  }
}
