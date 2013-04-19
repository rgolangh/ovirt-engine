package org.ovirt.engine.core.common.businessentities;

import java.io.Serializable;
import java.util.Date;

import org.ovirt.engine.core.common.action.VdcActionParametersBase;
import org.ovirt.engine.core.common.action.VdcActionType;
import org.ovirt.engine.core.common.asynctasks.AsyncTaskType;
import org.ovirt.engine.core.common.utils.ObjectUtils;
import org.ovirt.engine.core.compat.Guid;

public class AsyncTasks implements Serializable {
    private static final long serialVersionUID = 5913365704117183118L;



    public AsyncTasks() {
    }

    public AsyncTasks(VdcActionType action_type, AsyncTaskResultEnum result, AsyncTaskStatusEnum status, Guid vdsmTaskId,
            VdcActionParametersBase parentParameters, VdcActionParametersBase taskParameters, Guid stepId, Guid commandId, Guid storagePoolId, AsyncTaskType taskType) {
        this.actionType = action_type;
        this.result = result;
        this.status = status;
        this.vdsmTaskId = vdsmTaskId;
        this.actionParameters = parentParameters;
        this.taskParameters = taskParameters;
        this.stepId = stepId;
        this.startTime = new Date();
        this.commandId = commandId;
        this.storagePoolId = storagePoolId;
        this.taskId = Guid.newGuid();
        this.taskType = taskType;
    }

    private VdcActionType actionType = VdcActionType.forValue(0);

    public VdcActionType getaction_type() {
        return this.actionType;
    }

    public void setaction_type(VdcActionType value) {
        this.actionType = value;
    }

    private AsyncTaskResultEnum result = AsyncTaskResultEnum.forValue(0);


    private Date startTime;


    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public AsyncTaskResultEnum getresult() {
        return this.result;
    }

    public void setresult(AsyncTaskResultEnum value) {
        this.result = value;
    }

    private AsyncTaskStatusEnum status = AsyncTaskStatusEnum.forValue(0);

    public AsyncTaskStatusEnum getstatus() {
        return this.status;
    }

    public void setstatus(AsyncTaskStatusEnum value) {
        this.status = value;
    }

    private Guid vdsmTaskId = Guid.Empty;

    public Guid getVdsmTaskId() {
        return this.vdsmTaskId;
    }

    public void setVdsmTaskId(Guid value) {
        this.vdsmTaskId = value;
    }

    private Guid taskId;

    public Guid getTaskId() {
        return this.taskId;
    }

    public void setTaskId(Guid value) {
        this.taskId = value;
    }

    private VdcActionParametersBase actionParameters;

    public VdcActionParametersBase getActionParameters() {
        return this.actionParameters;
    }

    public void setActionParameters(VdcActionParametersBase value) {
        this.actionParameters = value;
    }

    private VdcActionParametersBase taskParameters;

    public VdcActionParametersBase getTaskParameters() {
        return taskParameters;
    }

    public void setTaskParameters(VdcActionParametersBase taskParameters) {
        this.taskParameters = taskParameters;
    }

    private Guid stepId;

    public Guid getStepId() {
        return this.stepId;
    }

    public void setStepId(Guid stepId) {
        this.stepId = stepId;
    }

    private Guid commandId = Guid.Empty;

    public Guid getCommandId() {
        return commandId;
    }

    public void setCommandId(Guid commandId) {
        this.commandId = commandId;
    }

    public Guid getStoragePoolId() {
        return storagePoolId;
    }

    public void setStoragePoolId(Guid storagePoolId) {
        this.storagePoolId = storagePoolId;
    }

    private Guid storagePoolId;

    public AsyncTaskType getTaskType() {
        return taskType;
    }

    public void setTaskType(AsyncTaskType taskType) {
        this.taskType = taskType;
    }

    private AsyncTaskType taskType;

    @Override
    public int hashCode() {
        final int prime = 31;
        int results = 1;
        results = prime * results + ((vdsmTaskId == null) ? 0 : vdsmTaskId.hashCode());
        results = prime * results + ((stepId == null) ? 0 : stepId.hashCode());
        results = prime * results + ((commandId == null) ? 0 : commandId.hashCode());
        results = prime * results + ((actionParameters == null) ? 0 : actionParameters.hashCode());
        results = prime * results + ((actionType == null) ? 0 : actionType.hashCode());
        results = prime * results + ((result == null) ? 0 : result.hashCode());
        results = prime * results + ((status == null) ? 0 : status.hashCode());
        results = prime * results + ((startTime == null) ? 0 : startTime.hashCode());
        results = prime * results + ((storagePoolId == null) ? 0 : storagePoolId.hashCode());
        results = prime * results + ((taskType == null) ? 0 : taskType.hashCode());
        return results;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        AsyncTasks other = (AsyncTasks) obj;
        return (ObjectUtils.objectsEqual(vdsmTaskId, other.vdsmTaskId)
                && ObjectUtils.objectsEqual(taskId, other.taskId)
                && ObjectUtils.objectsEqual(stepId, other.stepId)
                && ObjectUtils.objectsEqual(commandId, other.commandId)
                && ObjectUtils.objectsEqual(actionParameters, other.actionParameters)
                && actionType == other.actionType
                && result == other.result
                && status == other.status
                && ObjectUtils.objectsEqual(startTime, other.startTime)
                && ObjectUtils.objectsEqual(storagePoolId, other.storagePoolId)
                && ObjectUtils.objectsEqual(taskType, other.taskType));
    }
}
